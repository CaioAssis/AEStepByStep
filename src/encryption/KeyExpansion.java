package encryption;

public class KeyExpansion {

	/**
	 * Implementação da expansão de chave para gerar a próxima round key a partir da atual.
	 * @param round_key - a round key atual (matriz de bytes 4x4).
	 * @param round - número da rodada atual (de 0 a 9).
	 * @return retorna a próxima round key (matriz de bytes 4x4), que será usada no próximo ciclo.
	 */
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
	
	/**
	 * Gera uma nova palavra de 4 bytes baseada na palavra anterior.
	 * @param word - palavra da rodada anterior.
	 * @param round - número da rodada.
	 * @return retorna a nova palavra a ser usada como a primeira palavra da nova rouns key.
	 */
	public static byte[] generator(byte[] word, int round) {
		byte[] saida = new byte[4];
		saida = rot_word(word);
		saida = sub_word(saida);
		saida = rcon(saida, round);
		return saida;
	}
	
	/**
	 * Rotaciona os byts da palavra que entra 1 casa para a direita.
	 * @param word - palavra que entra.
	 * @return retorna a palavra que entrou com a roatação de bytes feita.
	 */
	public static byte[] rot_word(byte[] word){
		byte[] saida = new byte[4];
		for(int i=0; i<4; i++) {
			saida[i] = word[(i+1)%4];
		}
		return saida;
	}
	
	/**
	 * Faz a substituição dos bytes da palavra que entra pelos bytes referentes da tablea fixa S-Box.
	 * @param word - palavra que entra.
	 * @return retorna o reultado da substituição dos bytes da palavra que entrou pelos bytes referentes da S-Box.
	 */
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
	
	/**
	 * Faz a operação Rcon com uma palavra de 4 bytes.
	 * @param word - palavra que entra.
	 * @param round - número da rodada.
	 * @return retorna a palavra modificada pelo Rcon.
	 */
	public static byte[] rcon(byte[] word, int round){
		byte[] saida = new byte[4];
		byte[] rcon = FixedTables.get_round_rcon(round);
		
		for(int i=0; i<4; i++) {
			saida[i] = (byte)(word[i] ^ rcon[i]);
		}
		return saida;
	}

}
