package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class IEncryptMain {

	String matrix_phrase = "";
	VBox matrix_grid = new VBox(5);
	String key_matrix_phrase = "";
	VBox key_matrix_grid = new VBox(5);

	public Scene getScene(double width, double height) {
		StackPane root = new StackPane();
		root.setPadding(new Insets(20, 20, 20, 20));

		Scene scene = new Scene(root, width, height);

		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		scene.getRoot().getStyleClass().add("iencrypt-root");

		Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);
		
		Label phrase_label = new Components().label("Digite a frase a ser encriptada", StylesEnum.SUBTITLE);
		Label key_label = new Components().label("Digite a chave da encriptação", StylesEnum.SUBTITLE);

		TextArea phrase_txtarea = new Components().textArea("Digite a frase");
		phrase_txtarea.prefWidthProperty().bind(scene.widthProperty().multiply(0.5));
		Tables tables = new Tables();

		TextField key_txtfield = new Components().textField("Digite a chave");
		key_txtfield.prefWidthProperty().bind(scene.widthProperty().multiply(0.5));
		
		VBox phrase_grid = new VBox(5);
		HBox phrase_line = new HBox(20);
		
		phrase_grid.setAlignment(Pos.TOP_LEFT);
		matrix_grid = tables.matrix_table(matrix_phrase);
		phrase_line.getChildren().addAll(phrase_txtarea,matrix_grid);
		phrase_grid.getChildren().addAll(phrase_label, phrase_line);
		
		phrase_txtarea.textProperty().addListener((obs, aval, val) -> {
			matrix_phrase = val;
			matrix_grid = tables.matrix_table(matrix_phrase);
			phrase_line.getChildren().set(1, matrix_grid);
		});
		
		Line separator = new Line(0, 0, 0, 0); // x1, y1, x2, y2 (linha horizontal de 200px)
		separator.setEndX(scene.widthProperty().doubleValue() - 40);
		separator.setStroke(Color.web("#c4bf89"));
		separator.setStrokeWidth(5);
		
		HBox key_matrix = new HBox(20);
		key_matrix.setAlignment(Pos.CENTER_LEFT);
		VBox key_grid = new VBox(5);
		key_grid.getChildren().addAll(key_label, key_txtfield);
		key_matrix_grid = tables.matrix_table(key_matrix_phrase);
		key_matrix.getChildren().addAll(key_grid, key_matrix_grid);
		
		key_txtfield.textProperty().addListener((obs, aval, val) -> {
			key_matrix_phrase = val;
			key_matrix_grid = tables.matrix_table(key_matrix_phrase);
			key_matrix.getChildren().set(1, key_matrix_grid);
		});

		HBox nav_grid = new HBox(scene.widthProperty().doubleValue()*0.4);
		scene.widthProperty().addListener((obs, oldVal, newVal) -> {
		    double largura = newVal.doubleValue();
		    double spacingDinamico = largura * 0.4;
		    nav_grid.setSpacing(spacingDinamico);
		    separator.setEndX(largura - 40);
		});
		VBox.setVgrow(nav_grid, Priority.ALWAYS);
		nav_grid.setAlignment(Pos.BOTTOM_CENTER);

		Button back_button = new Components().navButton("Voltar");
		back_button
				.setOnAction(e -> Main.mainStage.setScene(new Index().getScene(scene.getWidth(), scene.getHeight())));

		Button next_button = new Components().navButton("Próximo");
		next_button
		.setOnAction(e -> Main.mainStage.setScene(new IEncrypt2().getScene(scene.getWidth(), scene.getHeight(), key_matrix_grid, phraseHex(matrix_phrase), phraseHex(key_matrix_phrase))));

		nav_grid.getChildren().addAll(back_button, next_button);

		VBox vboxroot = new VBox(10);
		VBox bottom_box = new VBox(10);
		bottom_box.getChildren().addAll(nav_grid);
		bottom_box.setAlignment(Pos.BOTTOM_CENTER);

		vboxroot.getChildren().addAll(phrase_grid, separator, key_matrix, spacer, bottom_box);

		root.getChildren().addAll(vboxroot);
		return scene;
	}
	
	public static byte[][][] phraseHex(String input) {
		byte[] entrada = mainPackage.Main.convert_byte(input);
		int pointer = 0;
		int quantidade = (entrada.length / 16) + 1;
		byte[][][] matriz = new byte[quantidade][4][4];
		for (int h = 0; h < quantidade; h++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (entrada.length > pointer) {
						matriz[h][i][j] = entrada[pointer];
					} else {
						matriz[h][i][j] = (byte) ' ';
					}
					pointer++;
				}
			}
		}
		return matriz;
	}

}