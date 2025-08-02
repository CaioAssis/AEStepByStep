package interfaces;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ConfigScreen {

    public Scene getScene() {
        Button btn = new Button("Voltar");

        btn.setOnAction(e -> {
            // Volta para a tela inicial
            Main.mainStage.setScene(new HomeScreen().getScene());
        });

        StackPane root = new StackPane(btn);
        return new Scene(root, 300, 400);
    }
}
