package interfaces;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class IEncryptMain {

    public Scene getScene(double width, double height) {
    	StackPane root = new StackPane();
		Scene scene = new Scene(root, width, height);
		
        Button btn = new Button("Ir para tela de configurações");

        btn.setOnAction(e -> {
            // Troca para a tela de configurações
            Main.mainStage.setScene(new Index().getScene(scene.getWidth(),scene.getHeight()));
        });
        
        root.getChildren().add(btn);
        return scene;
    }
    
}