package mainPackage;
import encryption.Operations;

import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void main(String[] args) {

		String texto = "Eu sou um cara legal, voce nao acha?";

		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02x", b));
		}
		byte[][][] matriz = Operations.create_matrix(bytes);
		
		byte[] roundKey = { (byte) 0x9a, (byte) 0x9b, (byte) 0x9c, (byte) 0x9d, (byte) 0x9e, (byte) 0x9f, (byte) 0xa0,
				(byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0xa6, (byte) 0xa7, (byte) 0xa8,
				(byte) 0xa9 };
		
		//AddRoundKey (comentado)
		for(int i = 0; i<10; i++) {
			if(i==0) {
				//SubBytes (comentados)
				//Shiftrows
				//MixColumns
				//KeyExpansion
				//AddRoundKey
			}else if (i==9){
				//tudo do i=0 menos mix columns (comentado)
			}else {
				//todas as outras rodadas rapidão
			}
		}
	}

}
