package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab2 extends Application{

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Group(),690,690 );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
