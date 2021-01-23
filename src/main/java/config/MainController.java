package config;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class MainController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane wyswietl;

    @FXML
    private Pane searchPane;

    @FXML
    private TextField searchTextField;

    public int wywolanieTabeli = 0;

    public  double heightWyswietl;




    public void uslugaTable(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/usluga.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
        wywolanieTabeli = 1;
    }

    public void wlascicielTable(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/wlasciciel.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
       wywolanieTabeli = 2;
    }

    public void naprawaTable(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/naprawa.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
        wywolanieTabeli = 3;
    }

    public void update(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/FXML/update/updateMenu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Aktualizowanie danych z bazy danych");
        stage.show();
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
