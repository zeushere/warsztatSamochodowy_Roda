package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    static Pane mainPane = new Pane();
    static AnchorPane mainAnchorPane = new AnchorPane();

    public SceneController()
    {

    }

    public SceneController(Stage stage, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/FXML/main.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }

}
