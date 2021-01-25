package service.del;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class delMenu {
    @FXML
    private Pane pane;

    public void usluga(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delUsluga.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void wlasciciel(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delWlasciciel.fxml"));
        pane.getChildren().setAll(anchorPane);
    }

    public void naprawa(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delNaprawa.fxml"));
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
