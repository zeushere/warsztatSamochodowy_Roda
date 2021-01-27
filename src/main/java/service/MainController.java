package service;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainController{

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private GridPane gridPane;

    @FXML
    private GridPane wyswietl;



    public void uslugaTable(javafx.event.ActionEvent actionEvent) throws IOException {

        gridPane = FXMLLoader.load(getClass().getResource("/FXML/usluga.fxml"));
        wyswietl.getChildren().setAll(gridPane);

    }

    public void wlascicielTable(javafx.event.ActionEvent actionEvent) throws IOException {
        gridPane = FXMLLoader.load(getClass().getResource("/FXML/wlasciciel.fxml"));
        wyswietl.getChildren().setAll(gridPane);

    }

    public void naprawaTable(javafx.event.ActionEvent actionEvent) throws IOException {
        gridPane = FXMLLoader.load(getClass().getResource("/FXML/naprawa.fxml"));
        wyswietl.getChildren().setAll(gridPane);

    }

    public void update(javafx.event.ActionEvent actionEvent) throws IOException {
        SplitPane splitPane = FXMLLoader.load(getClass().getResource("/FXML/update/updateMenu.fxml"));
        wyswietl.getChildren().setAll(splitPane);

    }


    public void add(ActionEvent actionEvent) throws IOException{
        SplitPane splitPane = FXMLLoader.load(getClass().getResource("/FXML/add/addMenu.fxml"));
        wyswietl.getChildren().setAll(splitPane);
    }

    public void del(ActionEvent actionEvent) throws IOException{
        SplitPane splitPane = FXMLLoader.load(getClass().getResource("/FXML/del/delMenu.fxml"));
        wyswietl.getChildren().setAll(splitPane);
    }

    public void mainMenu(ActionEvent actionEvent) throws IOException {



        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();



        scene=(Scene) node.getScene();


        scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/main.fxml")));
        String pathName = "src/main/java/stylesheets/styles.css";
        File file = new File(pathName);
        if (file.exists()) {
            scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
        } else {
            System.out.println("Could not find css file: "+pathName);
        }


        stage.setScene(scene);





        stage.show();


    }
}
