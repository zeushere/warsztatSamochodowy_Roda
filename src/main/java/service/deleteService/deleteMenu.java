package service.deleteService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class deleteMenu {
    @FXML
    private Pane pane;

    public void usluga(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/deleteFXML/deleteUsluga.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wlasciciel(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/deleteFXML/deleteWlasciciel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void naprawa(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/deleteFXML/deleteNaprawa.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void egzemplarz(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/deleteFXML/delEgzemplarz.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wypozyczenia(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/deleteFXML/delWypozyczenia.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
