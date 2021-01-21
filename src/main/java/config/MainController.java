package config;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Pane wyswietl;

    public void klientTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/klient.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void pracTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/pracownik.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void prodTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/producent.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void egzTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/egzemplarz.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void wypoTable(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/FXML/wypozyczenie.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void add(ActionEvent actionEvent) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/add/addMenu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Dodawanie danych do bazy danych");
        stage.show();
    }

    public void del(ActionEvent actionEvent) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/del/delMenu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Usuwanie danych z bazy danych");
        stage.show();
    }
}
