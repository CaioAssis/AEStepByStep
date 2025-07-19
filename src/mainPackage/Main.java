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
//		byte[][][] matriz = create_matrix(bytes);
//		show_matrix(matriz);

		//System.out.printf("%02x", (byte)' ');

//		//separacao coordenadas hexa para s-box
//		int y = (byte)'j'/16;
//		int z = Math.floorMod((byte)'j',16);
//		
//		System.out.printf("%01d\n",y);
//		System.out.printf("%01d", z);

		//byte[][] uau = SBox.get_sbox();
		//SBox.show_sbox(uau);
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
}