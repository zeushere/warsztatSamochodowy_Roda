package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SceneController {


    public SceneController() {

    }

    public SceneController(Stage stage, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/main.fxml"));

        Parent root = fxmlLoader.load();
        stage.getScene().setRoot(root);


        String pathName = "src/main/java/stylesheets/styles.css";
        File file = new File(pathName);
        if (file.exists()) {
            scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        } else {
            System.out.println("Could not find css file: " + pathName);
        }


        stage.show();
    }

}
