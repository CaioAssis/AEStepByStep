package encryption;

public class KeyExpansion {

	public static byte[][] expansion(byte[][] round_key, int round) {
		byte[][] new_round_key = new byte[4][4];
		byte[] gen_word = new byte[4];
		for(int i=0; i<4; i++) {
			gen_word[i] = round_key[3][i];
		}
		
		gen_word = generator(gen_word, round);
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(i == 0) {
					new_round_key[i][j] = (byte)(round_key[i][j] ^ gen_word[j]);
				}
				else {
					new_round_key[i][j] = (byte)(round_key[i][j] ^ new_round_key[i-1][j]);
				}
			}
		}
		return new_round_key;
	}
	
	public static byte[] generator(byte[] word, int round) {
		byte[] saida = new byte[4];
		saida = rot_word(word);
		saida = sub_word(saida);
		saida = rcon(saida, round);
		return saida;
	}
	
	public static byte[] rot_word(byte[] word){
		byte[] saida = new byte[4];
		for(int i=0; i<4; i++) {
			saida[i] = word[(i+1)%4];
		}
		return saida;
	}
	
	public static byte[] sub_word(byte[] word) {
		byte[][] sbox = FixedTables.get_sbox();
		byte[] saida = new byte[4];
		int x,y;
		
		for(int i=0; i<4; i++) {
			x = (word[i]  & 0xFF)/ 16;
			y = (word[i]  & 0xFF)% 16;
			saida[i] = sbox[x][y];
		}
		return saida;
	}
	
	public static byte[] rcon(byte[] word, int round){
		byte[] saida = new byte[4];
		byte[] rcon = FixedTables.get_round_rcon(round);
		
		for(int i=0; i<4; i++) {
			saida[i] = (byte)(word[i] ^ rcon[i]);
		}
		return saida;
	}

}
