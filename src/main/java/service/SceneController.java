package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SceneController {


    public SceneController()
    {

    }

    public SceneController(Stage stage, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/FXML/main.fxml"));
        GridPane gridPane = fxmlLoader.load();

        scene = new Scene(gridPane);

        String pathName = "src/main/java/stylesheets/styles.css";
        File file = new File(pathName);
        if (file.exists()) {
            scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        } else {
            System.out.println("Could not find css file: "+pathName);
        }

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
