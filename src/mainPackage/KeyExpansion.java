package mainPackage;

public class KeyExpansion {

	public static byte[] rot_word(byte[] word){
		byte[] saida = new byte[4];
		for(int i=0; i<4; i++) {
			saida[i] = word[(i+1)%4];
		}
		return saida;
	}
	
	public static byte[] sub_word(byte[] word) {
		byte[][] sbox = SBox.get_sbox();
		byte[] saida = new byte[4];
		int x,y;
		
		for(int i=0; i<4; i++) {
			x = word[i] / 16;
			y = word[i] % 16;
			saida[i] = sbox[x][y];
		}
		
		return saida;
	}
	
	
	
	public static void main(String[] args) {
		byte[] in = {(byte)0x00, (byte)0x02, (byte)0x03, (byte)0x04, };
		//in = rot_word(in);
		in = sub_word(in);
		System.out.printf("%02x", in[0]);
	}
	
}
