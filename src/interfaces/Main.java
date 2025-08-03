package interfaces;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	double height=200, width=300;
	
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        primaryStage.setTitle("AEStepByStep");
        Image app_icon = new Image(getClass().getResourceAsStream("../assets/icon.png"));

        primaryStage.getIcons().add(app_icon);
        primaryStage.setScene(new Index().getScene(width, height));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
