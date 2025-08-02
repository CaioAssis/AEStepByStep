package interfaces;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	int height=200, width=300;
	
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        primaryStage.setTitle("AEStepByStep");

        primaryStage.setScene(new Index().getScene(width, height));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
