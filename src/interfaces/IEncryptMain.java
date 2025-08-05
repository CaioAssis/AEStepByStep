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

public class IEncryptMain {

	String matrix_phrase = "";
	VBox matrix_grid = new VBox(5);

	public Scene getScene(double width, double height) {
		StackPane root = new StackPane();
		root.setPadding(new Insets(20, 20, 20, 20));

		Scene scene = new Scene(root, width, height);

		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		scene.getRoot().getStyleClass().add("iencrypt-root");

		Label phrase_label = new Components().label("Digite a frase a ser encriptada", StylesEnum.SUBTITLE);
		Label key_label = new Components().label("Digite a chave da encriptação", StylesEnum.SUBTITLE);

		TextArea phrase_txtarea = new Components().textArea("Digite a frase");
		Tables tables = new Tables();

		TextField key_txtfield = new Components().textField("Digite a chave");
		key_txtfield.textProperty().addListener((obs, aval, val) -> System.out.println(val));

		// TableView<Table_content> tabela = matrix();

		HBox phrase_matrix = new HBox(10);
		VBox phrase_grid = new VBox(5);
		phrase_grid.getChildren().addAll(phrase_label, phrase_txtarea);
		phrase_grid.setAlignment(Pos.TOP_LEFT);
		matrix_grid = tables.matrix_table(matrix_phrase);
		phrase_matrix.getChildren().addAll(phrase_grid, matrix_grid);
		phrase_txtarea.textProperty().addListener((obs, aval, val) -> {matrix_phrase = val; matrix_grid = tables.matrix_table(matrix_phrase);phrase_matrix.getChildren().set(1, matrix_grid);});
		
		Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);

		HBox key_matrix = new HBox(10);
		VBox.setVgrow(key_matrix, Priority.ALWAYS);
		key_matrix.setAlignment(Pos.BOTTOM_CENTER);
		VBox key_grid = new VBox(5);
		key_grid.getChildren().addAll(key_label, key_txtfield);
		key_matrix.getChildren().addAll(key_grid);

		HBox nav_grid = new HBox(100);
		VBox.setVgrow(nav_grid, Priority.ALWAYS);
		nav_grid.setAlignment(Pos.BOTTOM_CENTER);

		Button back_button = new Components().navButton("Voltar");
		back_button
				.setOnAction(e -> Main.mainStage.setScene(new Index().getScene(scene.getWidth(), scene.getHeight())));

		Button next_button = new Components().navButton("Próximo");

		nav_grid.getChildren().addAll(back_button, next_button);

		VBox vboxroot = new VBox(10);
		VBox bottom_box = new VBox(10);
		bottom_box.getChildren().addAll(key_matrix, nav_grid);
		bottom_box.setAlignment(Pos.BOTTOM_CENTER);

		vboxroot.getChildren().addAll(phrase_matrix, spacer, bottom_box);

		root.getChildren().addAll(vboxroot);
		return scene;
	}

//    public static TableView<Table_content> matrix () {
//    	TableView<Table_content> table = new TableView<>();
//
//    	TableColumn<Table_content, String> b0 = new TableColumn<>("0");
//    	b0.setCellValueFactory(new PropertyValueFactory<>("b0"));
//    	
//    	TableColumn<Table_content, String> b1 = new TableColumn<>("1");
//    	b1.setCellValueFactory(new PropertyValueFactory<>("b1"));
//    	
//    	TableColumn<Table_content, String> b2 = new TableColumn<>("2");
//    	b2.setCellValueFactory(new PropertyValueFactory<>("b2"));
//    	
//    	TableColumn<Table_content, String> b3 = new TableColumn<>("3");
//    	b3.setCellValueFactory(new PropertyValueFactory<>("b3"));
//
//    	table.getColumns().addAll(b0, b1, b2, b3);
//    	
//    	ObservableList<Table_content> matrix = FXCollections.observableArrayList(
//    			new Table_content ("a", "b", "c", "d")
//    			);
//    	
//    	table.setItems(matrix);
//    	table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
//    	    Node header = table.lookup("TableHeaderRow");
//    	    if (header != null) {
//    	        header.setVisible(false);
//    	        header.setManaged(false);
//    	    }
//    	});
//    	return table;
//    }

}