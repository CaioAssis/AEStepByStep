package mainPackage;

import encryption.KeyExpansion;
import decryption.DOperations;

public class DecryptionMain {

	public static byte[][][] decrypt(byte[][][] matriz_inicial, byte[] roundKey_inicial) {
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
		for(int i=1; i<11; i++) {
				roundKey[i] = KeyExpansion.expansion(roundKey[i-1], i-1);
		}
		// AddRoundKey (comentado)
		byte[][][] matriz_estado = DOperations.addRoundKey(matriz_inicial, roundKey[10]);
		for (int i = 10; i > 0; i--) {
			if (i == 10) {
				// InvShiftrows (comentados)
				matriz_estado = DOperations.inv_shift_rows(matriz_estado);
				// InvSubBytes 
				matriz_estado = DOperations.inv_sub_byte(matriz_estado);
				// AddRoundKey
				matriz_estado = DOperations.addRoundKey(matriz_estado, roundKey[i-1]);
				// InvMixColumns	
				matriz_estado = DOperations.inv_mix_columns(matriz_estado);
				
			} else if (i == 1) {
				// Ultima rodada
				// InvShiftrows 
				matriz_estado = DOperations.inv_shift_rows(matriz_estado);
				// InvSubBytes 
				matriz_estado = DOperations.inv_sub_byte(matriz_estado);
				// AddRoundKey
				matriz_estado = DOperations.addRoundKey(matriz_estado, roundKey[i-1]);
			} else {
				// Resto
				// InvShiftrows
				matriz_estado = DOperations.inv_shift_rows(matriz_estado);
				// InvSubBytes 
				matriz_estado = DOperations.inv_sub_byte(matriz_estado);
				// AddRoundKey
				matriz_estado = DOperations.addRoundKey(matriz_estado, roundKey[i-1]);
				// InvMixColumns	
				matriz_estado = DOperations.inv_mix_columns(matriz_estado);
			}
		}
		
		return matriz_estado;

	}

}
