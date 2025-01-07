package org.example;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.datac.AllData;
import org.example.gui.MainScene;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        AllData.loadInitData();
        stage.setScene(new MainScene().buildScene(stage));
        stage.setTitle("DZRT Market");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}