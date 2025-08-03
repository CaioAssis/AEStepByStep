package interfaces;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	
	public TextField textField(String placeholder) {
		TextField txtfield = new TextField();
		txtfield.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		txtfield.setPromptText(placeholder);
		txtfield.getStyleClass().add("text-field");

		// txtarea.textProperty().addListener((obs, aval, val) ->
		// System.out.println(val));

		return txtfield;
	}

	public Label label(String text, StylesEnum style) {
		String selected_style = "label-body";
		switch (style) {
		case TITLE:
			selected_style = "label-title";
			break;
		case SUBTITLE:
			selected_style = "label-subtitle";
			break;
		case BODY:
			selected_style = "label-body";
			break;
		case ANNOTATION:
			selected_style = "label-annotation";
			break;
		}

		Label label = new Label();

		label.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		label.getStyleClass().add(selected_style);
		label.setText(text);

		return label;
	}
	
	public Button navButton(String text) {
		Button button = new Button();
		
		button.setText(text);
		button.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		button.getStyleClass().add("button-nav");
		
		return button;
	}
	
	public Button skipButton(String text) {
		Button button = new Button();
		
		button.setText(text);
		button.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		button.getStyleClass().add("button-skip");
		
		return button;
	}
}
