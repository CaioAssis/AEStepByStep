package encryption;

public class EOperations {

	/**
	 * Método que cria uma matriz com 3 dimensões a partir de um array de bytes, podendo ser uma ou várias matrizes de 4 linhas por 4 colunas.
	 * @param entrada - entra o array de bytes a ser transformado em matriz.
	 * @return retorna a matriz de 3 dimensões.
	 */
	public static byte[][][] create_matrix(byte[] entrada) {
		int pointer = 0;
		int quantidade = (entrada.length / 16) + 1;
		byte[][][] matriz = new byte[quantidade][4][4];
		for (int h = 0; h < quantidade; h++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (entrada.length > pointer) {
						matriz[h][i][j] = entrada[pointer];
					} else {
						matriz[h][i][j] = (byte) ' ';
					}
					pointer++;
				}
			}
		}
		return matriz;
	}

	/**
	 * Imprime uma matriz de 3 dimensões no console.
	 * @param matriz - matriz a ser mostrada.
	 */
	public static void show_matrix(byte[][][] matriz) {
		for (int h = 0; h < matriz.length; h++) {
			for (int i = 0; i < matriz[h].length; i++) {
				for (int j = 0; j < matriz[h][i].length; j++) {
					System.out.printf("[%02x] ", matriz[h][i][j]);
				}
				System.out.println("");
			}
			System.out.println("\n");
		}
	}

	/**
	 * Faz a substituição dos bytes de uma matriz de 3 dimensões pelos bytes da s-box, que é uma tabela fixa.
	 * @param entrada_matriz - entra a sequência de matrizes da matriz de estado
	 * @return retorna a sequência de matrizes da matriz de estado com a troca de bytes feita.
	 */
	public static byte[][][] sub_byte(byte[][][] entrada_matriz) {
		byte[][][] saida_matriz = new byte[entrada_matriz.length][entrada_matriz[0].length][entrada_matriz[0][0].length];

		byte[][] sbox = FixedTables.get_sbox();
		int x = 0, y = 0; 

		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int i = 0; i < entrada_matriz[h].length; i++) {
				for (int j = 0; j < entrada_matriz[h][i].length; j++) {
					y = (entrada_matriz[h][i][j] & 0xFF)/ 16;
					x = Math.floorMod(entrada_matriz[h][i][j] & 0xFF, 16);

					saida_matriz[h][i][j] = sbox[y][x];
				}
			}
		}

		return saida_matriz;
	}

	/**
	 * Faz a rotação dos itens das linhas uma casa para a esquerda, menos na primeira linha.
	 * @param entrada_matriz - entra a sequência de matrizes da matriz de estado
	 * @return retorna a sequência de matrizes da matriz de estado com a rotação das linhas feita.
	 */
	public static byte[][][] shift_rows(byte[][][] entrada_matriz) {
		byte[][][] saida_matriz = new byte[entrada_matriz.length][entrada_matriz[0].length][entrada_matriz[0][0].length];

		int x = 0;

		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int i = 0; i < entrada_matriz[h].length; i++) {
				for (int j = 0; j < entrada_matriz[h][i].length; j++) {

					saida_matriz[h][i][j] = entrada_matriz[h][i][Math.floorMod(x, 4)];
					x++;
				}
				x++;
			}
			x = 0;
		}

		return saida_matriz;
	}

	/**
	 * Faz XOR da sequência de matrizes da matriz de estado com a matriz fixa de mix columns.
	 * @param entrada_matriz - entra a sequência de matrizes da matriz de estado
	 * @return retorna a sequência de matrizes da matriz de estado com o XOR feito com a tabela fixa.
	 */
	public static byte[][][] mix_columns(byte[][][] entrada_matriz) {
		byte[][] fixedMatrix = FixedTables.get_mix_columns_matrix();
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
	 * Método que faz a multiplicação de dois bytes no campo finito GF(2⁸).
	 * @param a - primeiro byte da multiplicação (que será a matriz fixa do mix columns).
	 * @param b - segundo byte da multiplicação (que será a matriz de estado).
	 * @return retorna o resultado da multiplicação dos bytes no campo finito.
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
	 * Faz o XOR da matriz de estado com a chave da encriptação da rodada.
	 * @param entrada_matriz - Sequência de matrizes da matriz de estado.
	 * @param chave_entrada - Chave de encriptação.
	 * @return retorna o resultado do XOR da matriz de estado com a chave de cada rodada.
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