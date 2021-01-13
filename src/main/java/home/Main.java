package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    /**
     * The first method executed on application launch. This just loads the first stage and scene.
     * @param primaryStage is part of the automatic javafx setup and is not used
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getResource("/Fxml/home.fxml")));
        primaryStage.setTitle("Anagram Generator");
        primaryStage.setScene(new Scene(root, 854, 480));
        primaryStage.setMinHeight(150.0);
        primaryStage.setMinWidth(200.0);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
