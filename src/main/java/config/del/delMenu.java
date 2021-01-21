package config.del;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class delMenu {
    @FXML
    private Pane pane;

    public void klient(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delKlient.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void pracownik(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delPracownik.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void producent(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delProducent.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void egzemplarz(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delEgzemplarz.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wypozyczenia(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delWypozyczenia.fxml"));
        pane.getChildren().setAll(anchorPane);
    }
}
