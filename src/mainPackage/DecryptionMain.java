package mainPackage;

import java.nio.charset.StandardCharsets;

import encryption.KeyExpansion;
import encryption.EOperations;
import decryption.DOperations;

public class DecryptionMain {

	public static void main(String[] args) {
		String texto = "Eu sou um cara legal, voce nao acha?";

		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz_inicial = EOperations.create_matrix(bytes);

		byte[] roundKey_inicial = { (byte) 0x9a, (byte) 0x9b, (byte) 0x9c, (byte) 0x9d, (byte) 0x9e, (byte) 0x9f,
				(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0xa6, (byte) 0xa7,
				(byte) 0xa8, (byte) 0xa9 };
		//
		byte[][][] roundKey = new byte[11][4][4];
		int val = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				roundKey[0][i][j] = roundKey_inicial[val];
				val++;
			}
		}
		//

		// Guardar Chave do Key_expansion antes de iniciar, pois addroundkey vai vir decrescendo
		for(int i=0; i<11; i++) {
			if(i != 0) {
				roundKey[i] = KeyExpansion.expansion(roundKey[i-1], i);
			}
		}
		// AddRoundKey (comentado)
		byte[][][] matriz_estado = EOperations.addRoundKey(matriz_inicial, roundKey[10]);
		for (int i = 10; i > 0; i--) {
			if (i == 10) {
				// InvShiftrows (comentados)
				// InvSubBytes 
				// AddRoundKey
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey[i-1]);
				// InvMixColumns	
				
			} else if (i == 1) {
				// Ultima rodada
				// InvShiftrows
				// InvSubBytes 
				// AddRoundKey
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey[i-1]);
			} else {
				// Resto
				// InvShiftrows
				// InvSubBytes 
				// AddRoundKey
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey[i-1]);
				// InvMixColumns
			}
		}

		EOperations.show_matrix(matriz_estado);

	}

}
