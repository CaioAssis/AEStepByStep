package interfaces;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Components {

	public TextArea textArea(String placeholder) {
		TextArea txtarea = new TextArea();
		txtarea.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		txtarea.setPromptText(placeholder);
		txtarea.setWrapText(true);
		txtarea.getStyleClass().add("text-area");

		// txtarea.textProperty().addListener((obs, aval, val) ->
		// System.out.println(val));

		return txtarea;
	}

	public Label label(String text, StylesEnum style) {
		String selected_style = "label-body";
		switch (style) {
		case TITLE:
			selected_style = "label-title";
		case SUBTITLE:
			selected_style = "label-subtitle";
		case BODY:
			selected_style = "label-body";
		case ANNOTATION:
			selected_style = "label-annotation";
		}

		Label label = new Label();

		label.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		label.getStyleClass().add(selected_style);
		label.setText(text);

		return label;
	}
}
