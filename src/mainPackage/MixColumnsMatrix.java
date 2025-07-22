package mainPackage;

public class MixColumnsMatrix {
	public static byte[][] fixed_matrix() {
		byte[][] fixedMatrix = { { (byte) 0x02, (byte) 0x03, (byte) 0x01, (byte) 0x01 },
				{ (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x01 },
				{ (byte) 0x01, (byte) 0x01, (byte) 0x02, (byte) 0x03 },
				{ (byte) 0x03, (byte) 0x01, (byte) 0x01, (byte) 0x02 } };
		return fixedMatrix;
	}
}
