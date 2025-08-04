package interfaces;

import javax.print.DocFlavor.URL;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Index{
	public Scene getScene(double width, double height) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, width, height);
		
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		scene.getRoot().getStyleClass().add("index-root");
		
		Button btnEncrypt = new Components().navButton("Encriptação");
		btnEncrypt.setOnAction(
				e -> Main.mainStage.setScene(new IEncryptMain().getScene(scene.getWidth(),scene.getHeight())));
		Button btnDecrypt = new Components().navButton("Decriptação");
		btnDecrypt.setOnAction(
				e -> Main.mainStage.setScene(new IDecryptMain().getScene(scene.getWidth(),scene.getHeight())));

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