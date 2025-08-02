package interfaces;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class IDecryptMain {

    public Scene getScene(double width, double height) {
    	StackPane root = new StackPane();
		Scene scene = new Scene(root, width, height);
		
        Button btn = new Button("Voltar");

        btn.setOnAction(e -> {
            // Volta para a tela inicial
            Main.mainStage.setScene(new Index().getScene(scene.getWidth(),scene.getHeight()));
        });
        
        root.getChildren().add(btn);
        return scene;
    }
}
