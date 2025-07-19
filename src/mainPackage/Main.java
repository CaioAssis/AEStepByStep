package mainPackage;

import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
        String texto = "Eu sou um cara legal";

        // Passo 1: transformar a String em bytes
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        

        // Passo 2: transformar os bytes em uma string hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // "%02x" garante que sempre teremos dois dígitos (ex: '0a' ao invés de 'a')
            hexString.append(String.format("%02x", b));
        }
        byte[][] matriz = create_matrix(bytes);
        //System.out.printf("%02x", matriz[0][0]);
    }
	
	public static byte[][] create_matrix(byte[] entrada) {
		System.out.println("Entrou");
		int pointer = 0;
		byte[][] matriz= new byte[4][4];
		for(int i=0; i < 4; i++) {
			for(int j=0; j < 4; j++) {
				if(entrada.length > pointer) {
					matriz[j][i] = entrada[pointer];
					System.out.printf("[%02x] ", matriz[j][i]);
				pointer++;
				}
			}
			System.out.println("");
		}
		return matriz;
	}
}