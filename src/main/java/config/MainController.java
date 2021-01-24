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

public class MainController{

    @FXML
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane wyswietl;

    @FXML
    private Pane searchPane;

    @FXML
    private TextField searchTextField;


    public void uslugaTable(javafx.event.ActionEvent actionEvent) throws IOException {

        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/usluga.fxml"));
        wyswietl.getChildren().setAll(anchorPane);


    }

    public void wlascicielTable(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/wlasciciel.fxml"));
        wyswietl.getChildren().setAll(anchorPane);

    }

    public void naprawaTable(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/naprawa.fxml"));
        wyswietl.getChildren().setAll(anchorPane);

    }

    public void update(javafx.event.ActionEvent actionEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/update/updateMenu.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
        SceneController sceneController = new SceneController();

    }


    public void add(ActionEvent actionEvent) throws IOException{
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addMenu.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void del(ActionEvent actionEvent) throws IOException{
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delMenu.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }
}
