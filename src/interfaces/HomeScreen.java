package interfaces;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HomeScreen {

    public Scene getScene() {
        Button btn = new Button("Ir para tela de configurações");

        btn.setOnAction(e -> {
            // Troca para a tela de configurações
            Main.mainStage.setScene(new ConfigScreen().getScene());
        });

        StackPane root = new StackPane(btn);
        return new Scene(root, 300, 200);
    }
    
}