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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IEncryptMain {

    public Scene getScene(double width, double height) {
    	StackPane root = new StackPane();
    	root.setPadding(new Insets(20,20,20,20));
    	
		Scene scene = new Scene(root, width, height);

		Label phrase_label = new Components().label("Digite a frase a ser encriptada",StylesEnum.SUBTITLE);
		Label key_label = new Components().label("Digite a chave da encriptação",StylesEnum.SUBTITLE);
		TextArea phrase_txtarea = new Components().textArea("Digite a frase");
		phrase_txtarea.textProperty().addListener((obs, aval, val) -> System.out.println(val));
		TextField key_txtfield = new Components().textField("Digite a chave");
		key_txtfield.textProperty().addListener((obs, aval, val) -> System.out.println(val));
		

        Button btn = new Button("Ir para tela de configurações");

        btn.setOnAction(e -> {
            // Troca para a tela de configurações
            Main.mainStage.setScene(new Index().getScene(scene.getWidth(),scene.getHeight()));
        });
        
        HBox phrase_matrix = new HBox(10);
        	VBox phrase_grid = new VBox(5);
        	phrase_grid.getChildren().addAll(phrase_label,phrase_txtarea);
        	phrase_grid.setAlignment(Pos.TOP_LEFT);
        phrase_matrix.getChildren().addAll(phrase_grid);
        
        HBox key_matrix = new HBox(10);
    		VBox.setVgrow(key_matrix, Priority.ALWAYS);//problema posicionamento grid
    		key_matrix.setAlignment(Pos.BOTTOM_CENTER);//problema posicionamento grid
        	VBox key_grid = new VBox(5);
        	key_grid.getChildren().addAll(key_label,key_txtfield);
        key_matrix.getChildren().addAll(key_grid);
        
        HBox nav_grid = new HBox(100);
        VBox.setVgrow(nav_grid, Priority.ALWAYS);
        nav_grid.setAlignment(Pos.BOTTOM_CENTER);
        
		Button back_button = new Components().navButton("Voltar");
		back_button.setOnAction(
				e -> Main.mainStage.setScene(new Index().getScene(scene.getWidth(),scene.getHeight())));
		
		Button next_button = new Components().navButton("Próximo");
		
        nav_grid.getChildren().addAll(back_button, next_button);
        
        VBox vboxroot = new VBox(10);
        vboxroot.getChildren().addAll(phrase_matrix,key_matrix,nav_grid);
		
        root.getChildren().addAll(vboxroot);
        return scene;
    }
    
}