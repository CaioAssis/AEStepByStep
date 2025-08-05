package interfaces;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Tables {
	
	public VBox matrix_table(String input) {
		System.out.println(input);
		VBox matrix = new VBox(5);
		HBox[] item = new HBox[4];
		byte[] text_byte = mainPackage.Main.convert_byte(input);
		Label[] text_label = new Label[16];
		Components comp = new Components();
		matrix.getChildren().clear();
		int h = 0;
		for(int i = 0; i<4; i++) {
			item[i] = new HBox(5);
			for(int j = 0; j<4; j++) {
				if (input.length() > h) {
					text_label[h] = comp.label(String.format("%02X", text_byte[h]), StylesEnum.BODY);
				} else {
					text_label[h] = comp.label(" ", StylesEnum.BODY);
				}

				item[i].getChildren().add(text_label[h]);
				h++;
			}
			matrix.getChildren().add(item[i]);
		}
		
		return matrix;
	}

}
