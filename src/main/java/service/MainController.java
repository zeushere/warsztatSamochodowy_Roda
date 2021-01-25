package service;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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

    }


    public void add(ActionEvent actionEvent) throws IOException{
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/add/addMenu.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void del(ActionEvent actionEvent) throws IOException{
        anchorPane = FXMLLoader.load(getClass().getResource("/FXML/del/delMenu.fxml"));
        wyswietl.getChildren().setAll(anchorPane);
    }

    public void mainMenu(ActionEvent actionEvent) throws IOException {

        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/main.fxml")));
        stage.setScene(scene);
        stage.show();


    }
}