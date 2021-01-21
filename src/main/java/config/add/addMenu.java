package config.add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class addMenu {

    @FXML
    private Pane pane;

    public void klient(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addKlient.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void pracownik(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addPracownik.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void producent(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addProducent.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void egzemplarz(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addEgzemplarz.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wypozyczenia(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addWypozyczenia.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
