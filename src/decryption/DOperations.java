package decryption;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import encryption.EOperations;

public class DOperations {
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

	public static void main(String[] Args) {
		String texto = "abcdabcdabcdabcdabcd";

		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz_inicial = EOperations.create_matrix(bytes);
		EOperations.show_matrix(matriz_inicial);
		System.out.println("");
		matriz_inicial = inv_shift_rows(matriz_inicial);
		
		EOperations.show_matrix(matriz_inicial);
	}
}
