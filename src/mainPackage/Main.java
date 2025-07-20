package mainPackage;

import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");

		String texto = "Eu sou um cara legal, voce nao acha?";

		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz = create_matrix(bytes);
//		show_matrix(matriz);

		// System.out.printf("%02x", (byte)' ');

		// byte[][] uau = SBox.get_sbox();
		// SBox.show_sbox(uau);

		byte[][][] teste = shift_rows(matriz);
		show_matrix(matriz);
		System.out.println("================");
		show_matrix(teste);
	}

	public static byte[][][] create_matrix(byte[] entrada) {
		System.out.println("Entrou");
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

	public static byte[][][] sub_byte(byte[][][] entrada_matriz) {
		byte[][][] saida_matriz = new byte[entrada_matriz.length][entrada_matriz[0].length][entrada_matriz[0][0].length];

		byte[][] sbox = SBox.get_sbox();
		int x, y;

		for (int h = 0; h < entrada_matriz.length; h++) {
			for (int i = 0; i < entrada_matriz[h].length; i++) {
				for (int j = 0; j < entrada_matriz[h][i].length; j++) {
					x = entrada_matriz[h][i][j] / 16;
					y = Math.floorMod(entrada_matriz[h][i][j], 16);
					saida_matriz[h][i][j] = sbox[y][x];
				}
			}
		}

		return saida_matriz;
	}

	public static byte [][][] shift_rows(byte[][][] entrada_matriz){
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
}