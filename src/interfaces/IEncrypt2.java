package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class IEncrypt2 {
	//Add Round Key
	//Estamos usando matriz 3D, mas só utilizaremos 2D. 3D para tratamento da matriz.
	//Vamos fazer o AES todo logo no início, os processos que serão pausados durante o programa serão feitos paralelamente.
	//Todas as funções usadas estão usando matriz 3D.
	//Pop-up para explicações.
	//Explicação por hover do mouse.
	//Validação dos campos iniciais (frase e chave) para ter a quantidade correta de caracteres.
	//Limitar a quantidade de caracteres da chave (Listener?).
	//Colocar botão Home e Skip.
	public Scene getScene(double width, double height, VBox matrix, byte[][][] phrase, byte[][][] round_key) {
		StackPane root = new StackPane();
		root.setPadding(new Insets(20, 20, 20, 20));

		Scene scene = new Scene(root, width, height);
		
		scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		scene.getRoot().getStyleClass().add("iencrypt-root");
		
		
		
		System.out.printf("%02x", round_key[0][0]);
		System.out.printf("%02x", phrase[0][0]);
		
		HBox nav_grid = new HBox(scene.widthProperty().doubleValue()*0.4);
		scene.widthProperty().addListener((obs, oldVal, newVal) -> {
		    double largura = newVal.doubleValue();
		    double spacingDinamico = largura * 0.4;
		    nav_grid.setSpacing(spacingDinamico);
		});
		VBox.setVgrow(nav_grid, Priority.ALWAYS);
		nav_grid.setAlignment(Pos.BOTTOM_CENTER);

		Button back_button = new Components().navButton("Voltar");
		back_button
				.setOnAction(e -> Main.mainStage.setScene(new Index().getScene(scene.getWidth(), scene.getHeight())));

		Button next_button = new Components().navButton("Próximo");

		nav_grid.getChildren().addAll(back_button, next_button);

		VBox vboxroot = new VBox(10);
		VBox bottom_box = new VBox(10);
		bottom_box.getChildren().addAll(nav_grid);
		bottom_box.setAlignment(Pos.BOTTOM_CENTER);

		vboxroot.getChildren().addAll(matrix, bottom_box);

		root.getChildren().addAll(vboxroot);
		
		return scene;
		
	}

}
