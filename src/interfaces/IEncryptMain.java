package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IEncryptMain {

    public Scene getScene(double width, double height) {
    	StackPane root = new StackPane();
    	
		Scene scene = new Scene(root, width, height);

		Label phrase_label = new Components().label("Digite a frase a ser encriptada",StylesEnum.SUBTITLE);
		TextArea txtarea = new Components().textArea("placeholder");
		txtarea.textProperty().addListener((obs, aval, val) -> System.out.println(val));
		

        Button btn = new Button("Ir para tela de configurações");

        btn.setOnAction(e -> {
            // Troca para a tela de configurações
            Main.mainStage.setScene(new Index().getScene(scene.getWidth(),scene.getHeight()));
        });
        
        HBox phrase_matrix = new HBox(10);
        	VBox phrase_grid = new VBox(5);
        	phrase_grid.getChildren().addAll(phrase_label,txtarea);
        	phrase_grid.setAlignment(Pos.TOP_LEFT);
        phrase_matrix.getChildren().addAll(phrase_grid);
        HBox key_grid = new HBox(10);
        HBox nav_grid = new HBox(10); 
        
        VBox vboxroot = new VBox(10);
        vboxroot.getChildren().addAll(phrase_matrix,key_grid,nav_grid);
        vboxroot.setAlignment(Pos.CENTER);
		
        root.getChildren().add(vboxroot);
        return scene;
    }
    
}