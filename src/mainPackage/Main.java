package mainPackage;

import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");

		String texto = "Eu sou um cara legal, voce nao acha?";

		// Passo 1: transformar a String em bytes
		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

		// Passo 2: transformar os bytes em uma string hexadecimal
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			// "%02x" garante que sempre teremos dois dígitos (ex: '0a' ao invés de 'a')
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz = create_matrix(bytes);
		//System.out.printf("%02x", matriz[0][0]);
	}

	public static byte[][][] create_matrix(byte[] entrada) {
		System.out.println("Entrou");
		int pointer = 0;
		int quantidade = (entrada.length / 16)+1;
		byte[][][] matriz = new byte[quantidade][4][4];
		for (int h = 0; h < quantidade; h++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (entrada.length > pointer) {
						matriz[h][j][i] = entrada[pointer];
						System.out.printf("[%02x] ", matriz[h][j][i]);
					}
					else {
						matriz[h][j][i] = (byte)' ';
						System.out.printf("[%02x] ", matriz[h][j][i]);
					}
					pointer++;
				}
				System.out.println("");
			}
			System.out.println("fim");
		}
		return matriz;
	}
}