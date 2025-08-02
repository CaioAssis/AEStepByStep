package interfaces;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Components {

	public TextArea textArea() {
		TextArea txtarea = new TextArea();
		txtarea.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		txtarea.setPromptText("texto aqui");
		//txtarea.textProperty().addListener((obs, aval, val) -> System.out.println(val));
		txtarea.setWrapText(true);
		txtarea.getStyleClass().add("text-area");
		
		return txtarea;
	}
	
	public Label label(String text) {
		Label label = new Label();
		
		label.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		label.getStyleClass().add("label-annotation");
		label.setText(text);
		
		return label;
	}
}
