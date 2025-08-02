package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Index{
	public Scene getScene(int width, int height) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, width, height);
		Button btnEncrypt = new Button("Encriptação");
		btnEncrypt.setOnAction(e -> teste(scene));
		Button btnDecrypt = new Button("Decriptação");

		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(btnEncrypt, btnDecrypt);
		hbox.setAlignment(Pos.CENTER);

		root.getChildren().add(hbox);

		return scene;
	}
	
	public static void teste(Scene scene) {
		System.out.println(scene.getWidth());
	}
}