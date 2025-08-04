package interfaces;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	double height=600, width=800;
	
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        //mainStage.initStyle(StageStyle.UNDECORATED);
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
