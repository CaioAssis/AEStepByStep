package interfaces;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Index{
	public void telainicial(Stage primaryStage) {
		Button btnEncrypt = new Button("Encriptação");
		btnEncrypt.setOnAction(e -> System.out.println("Olá, JavaFX!"));
		Button btnDecrypt = new Button("Decriptação");

		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(btnEncrypt, btnDecrypt);
		hbox.setAlignment(Pos.CENTER);

		StackPane root = new StackPane(hbox);
		Scene scene = new Scene(root, 300, 200);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}