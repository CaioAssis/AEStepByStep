package mainPackage;

import encryption.EOperations;
import encryption.KeyExpansion;

public class EncryptionMain {
	
	/**
	 * Aplica os métodos de encriptação e as repetições deles.
	 * @param matriz_inicial - sequência de matrizes da matriz estado.
	 * @param roundKey_inicial - chave inicial da encriptação.
	 * @return retorna a matriz encriptada da frase inicial.
	 */
	public static byte[][][] encrypt(byte[][][] matriz_inicial, byte[] roundKey_inicial) {
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
		byte[][][] matriz_estado = EOperations.addRoundKey(matriz_inicial, roundKey);
		for(int i = 0; i<10; i++) {
			if(i==0) {
				//SubBytes (comentados)
				matriz_estado = EOperations.sub_byte(matriz_estado);
				//Shiftrows
				matriz_estado = EOperations.shift_rows(matriz_estado);
				//MixColumns
				matriz_estado = EOperations.mix_columns(matriz_estado);
				//KeyExpansion
				roundKey = KeyExpansion.expansion(roundKey, i);
				//AddRoundKey
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey);
			}
			else if (i==9){
				//Ultima rodada
				matriz_estado = EOperations.sub_byte(matriz_estado);
				matriz_estado = EOperations.shift_rows(matriz_estado);
				roundKey = KeyExpansion.expansion(roundKey, i);
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey);
			}
			else {
				//Resto
				matriz_estado = EOperations.sub_byte(matriz_estado);
				matriz_estado = EOperations.shift_rows(matriz_estado);
				matriz_estado = EOperations.mix_columns(matriz_estado);
				roundKey = KeyExpansion.expansion(roundKey, i);
				matriz_estado = EOperations.addRoundKey(matriz_estado, roundKey);
			}
		}
		
		return matriz_estado;
	}

}
