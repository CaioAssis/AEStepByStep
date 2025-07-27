package mainPackage;
import encryption.Operations;
import encryption.KeyExpansion;


import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void main(String[] args) {

		String texto = "Eu sou um cara legal, voce nao acha?";

		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz_inicial = Operations.create_matrix(bytes);
		
		byte[] roundKey_inicial = { (byte) 0x9a, (byte) 0x9b, (byte) 0x9c, (byte) 0x9d, (byte) 0x9e, (byte) 0x9f, (byte) 0xa0,
				(byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0xa6, (byte) 0xa7, (byte) 0xa8,
				(byte) 0xa9 };
		//
		byte[][] roundKey = new byte[4][4];
		int val=0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				roundKey[i][j] = roundKey_inicial[val];
				val++;
			}
		}
		//
		
		//AddRoundKey (comentado)
		byte[][][] matriz_estado = Operations.addRoundKey(matriz_inicial, roundKey);
		for(int i = 0; i<10; i++) {
			if(i==0) {
				//SubBytes (comentados)
				matriz_estado = Operations.sub_byte(matriz_estado);
				//Shiftrows
				matriz_estado = Operations.shift_rows(matriz_estado);
				//MixColumns
				matriz_estado = Operations.mix_columns(matriz_estado);
				//KeyExpansion
				roundKey = KeyExpansion.expansion(roundKey, i);
				//AddRoundKey
				matriz_estado = Operations.addRoundKey(matriz_estado, roundKey);
			}
			else if (i==9){
				//Ultima rodada
				matriz_estado = Operations.sub_byte(matriz_estado);
				matriz_estado = Operations.shift_rows(matriz_estado);
				roundKey = KeyExpansion.expansion(roundKey, i);
				matriz_estado = Operations.addRoundKey(matriz_estado, roundKey);
			}
			else {
				//Resto
				matriz_estado = Operations.sub_byte(matriz_estado);
				matriz_estado = Operations.shift_rows(matriz_estado);
				matriz_estado = Operations.mix_columns(matriz_estado);
				roundKey = KeyExpansion.expansion(roundKey, i);
				matriz_estado = Operations.addRoundKey(matriz_estado, roundKey);
			}
		}
		
		Operations.show_matrix(matriz_estado);
	}

}
