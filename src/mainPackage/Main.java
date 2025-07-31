package mainPackage;

import java.nio.charset.StandardCharsets;

import encryption.EOperations;

public class Main {
	public static void main(String[] args) {
		String texto = "eu sou legal demais da  conta";
		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz_inicial = EOperations.create_matrix(bytes);

		byte[] roundKey_inicial = { (byte) 0x30, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35,
				(byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x30, (byte) 0x31, (byte) 0x32, (byte) 0x33,
				(byte) 0x34, (byte) 0x35 };

		EOperations.show_matrix(matriz_inicial);
		System.out.println("=================");

		matriz_inicial = EncryptionMain.encrypt(matriz_inicial, roundKey_inicial);
		EOperations.show_matrix(matriz_inicial);
		System.out.println("=================");

		matriz_inicial = DecryptionMain.decrypt(matriz_inicial, roundKey_inicial);
		EOperations.show_matrix(matriz_inicial);
		System.out.println("=================");
		System.out.println(translator(matriz_inicial));

	}

	/**
	 * Transforma uma sequência de matrizes de bytes hexadecimais em uma string com os caracteres em UTF-8.
	 * @param matriz - sequência de matrizes a ser traduzidas.
	 * @return retorna uma string com os caracteres em UTF-8.
	 */
	public static String translator(byte[][][] matriz) {
		// Converte a matriz 4x4 (ou qualquer dimensão) em um vetor
		int profundidade = matriz.length;
		int linhas = matriz[0].length;
		int colunas = matriz[0][0].length;
		byte[] vetor = new byte[profundidade * linhas * colunas];

		int k = 0;
		for (int h = 0; h < profundidade; h++) {
			for (int i = 0; i < linhas; i++) {
				for (int j = 0; j < colunas; j++) {
					vetor[k++] = matriz[h][i][j];
				}
			}
		}

		// Converte o vetor de bytes em uma String usando UTF-8
		try {
			return new String(vetor, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
