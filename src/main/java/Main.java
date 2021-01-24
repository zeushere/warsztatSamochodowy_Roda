import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Warsztat Samochodowy");

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/login.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });


    }
}