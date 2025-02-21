package service.updateService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class updateMenu {

    @FXML
    Stage stage;
    @FXML
    private Pane pane;


    public void usluga(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/updateFXML/updateUsluga.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wlasciciel(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/updateFXML/updateWlasciciel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void naprawa(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/updateFXML/updateNaprawa.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

}
