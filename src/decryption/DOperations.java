package decryption;

import java.nio.charset.StandardCharsets;

import encryption.EOperations;

/**
 * Classe responsavel pelas operacoes de decriptacao
 */
public class DOperations {
	/**
	 * Metodo responsavel por fazer a movimentacao 
	 * dos bytes da matriz estado de forma decrescente 
	 * ([0][1][2][3] para [3][0][1][2])
	 * @param entrada_matriz - array de matrizes de estado
	 * @return array de matrizes de estado apos a movimentacao de colunas
	 */
	public static byte[][][] inv_shift_rows(byte[][][] entrada_matriz) {
		byte[][][] saida_matriz = new byte[entrada_matriz.length][entrada_matriz[0].length][entrada_matriz[0][0].length];

		int x = 4;

		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int i = 0; i < entrada_matriz[h].length; i++) {
				for (int j = 0; j < entrada_matriz[h][i].length; j++) {

					saida_matriz[h][i][j] = entrada_matriz[h][i][Math.floorMod(x, 4)];
					x++;
				}
				x -= 5;
			}
			x = 4;
		}

		return saida_matriz;
	}
	
	/**
	 * Metodo responsavel por fazer a substituicao 
	 * dos bytes da matriz, em relacao a tabela inv sbox
	 * @param entrada_matriz - array de matrizes de estado
	 * @return array de matrizes de estado apos a substituicao de bytes
	 */
	public static byte[][][] inv_sub_byte(byte[][][] entrada_matriz) {
		byte[][][] saida_matriz = new byte[entrada_matriz.length][entrada_matriz[0].length][entrada_matriz[0][0].length];

		byte[][] inv_sbox = decryption.FixedTables.get_inv_sbox();
		int x = 0, y = 0; 

		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int i = 0; i < entrada_matriz[h].length; i++) {
				for (int j = 0; j < entrada_matriz[h][i].length; j++) {
					y = (entrada_matriz[h][i][j] & 0xFF)/ 16;
					x = Math.floorMod(entrada_matriz[h][i][j] & 0xFF, 16);

					saida_matriz[h][i][j] = inv_sbox[y][x];
				}
			}
		}

		return saida_matriz;
	}
	
	/**
	 * Metodo responsavel por fazer o calculo da mistura de colunas do AES
	 * @param entrada_matriz - array de matrizes de estado 
	 * @return array de matrizes de estado apos a mistura de colunas
	 */
	public static byte[][][] inv_mix_columns(byte[][][] entrada_matriz) {
		byte[][] fixedMatrix = decryption.FixedTables.get_inv_mix_columns_matrix();
		byte[][][] saida_matriz = new byte[entrada_matriz.length][4][4];
		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int col = 0; col < 4; col++) {
				for (int row = 0; row < 4; row++) {
					byte val = 0;
					for (int i = 0; i < 4; i++) {
						val ^= gfMultiply(fixedMatrix[row][i], entrada_matriz[h][i][col]);
					}
					saida_matriz[h][row][col] = val;
				}
			}
		}
		return saida_matriz;
	}

	/**
	 * Metodo responsavel por realizar a multiplicacao dos 
	 * bytes presentes na operacao mixcolumns com a matriz fixa
	 * dessa mesma operacao. Operacao feita byte a byte.
	 * @param a - byte referente a matriz fixa
	 * @param b - byte referente a matriz estado
	 * @return byte resultante da operacao
	 */
	public static byte gfMultiply(byte a, byte b) {
		byte p = 0;
		for (int i = 0; i < 8; i++) {
			if ((b & 1) != 0) {
				p ^= a;
			}
			int hiBitSet = a & 0x80;
			a <<= 1;
			if (hiBitSet != 0) {
				a ^= 0x1B;
			}
			b >>= 1;
		}
		return p;
	}

	/**
	 * Metodo responsavel por fazer operacao XOR entre a chave da rodada e a matriz estado
	 * @param entrada_matriz - array de matrizes de estado
	 * @param chave_entrada - matriz da chave da rodada
	 * @return array de matrizes de estado apos a operacao XOR
	 */
	public static byte[][][] addRoundKey(byte[][][] entrada_matriz, byte[][] chave_entrada){
		byte[][][] saida_matriz = new byte[entrada_matriz.length][4][4];
		
		for(int h = 0; h < entrada_matriz.length; h++) {
			for(int i = 0; i < entrada_matriz[h].length; i++) {
				for(int j = 0; j < entrada_matriz[h][i].length; j++) {
					saida_matriz[h][i][j] = (byte) (entrada_matriz[h][i][j] ^ chave_entrada[i][j]);
				}
			}
		}
		
		return saida_matriz;
	}
}
