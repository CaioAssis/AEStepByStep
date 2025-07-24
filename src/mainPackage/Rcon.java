package mainPackage;

public class Rcon {
	/**
	 * Gera a chave Rcon usada na expansão de chaves AES.
	 * @param round - Numero da rodada para gerar o Rcon. 0 = rodada 1
	 * @return Vetor de bytes contendo os valores de Rcon.
	 */
	public static byte[] get_round_rcon(int round) {
		byte[] rcon = new byte[4];
		byte buffer = 0x01;
		
		for(int i=0; i<round; i++) {
			buffer = (byte) (((buffer & 0xFF) << 1) ^ (((buffer & 0x80) != 0) ? 0x1B : 0x00));
		}
		rcon[0] = buffer;
		
		//byte 1~3 = 00
		for(int i=1; i<4; i++) {
			rcon[i] = (byte)0x00;
		}
		
		return rcon;
	}
	
	public static void main(String[] args) {
		byte[] meupau = new byte[4];
		meupau = get_round_rcon(8);
		System.out.printf("[%02x]", meupau[0]);
	}
}
