package config;

import connect.DbConnect;
import database.Usluga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UslugaController implements Initializable {

    @FXML
    private TableView<Usluga> uslugaTab;
    @FXML
    private TableColumn<Usluga, String> uslugaNazwa;
    @FXML
    private TableColumn<Usluga, String> uslugaRodzaj;


    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Usluga> uslugi;

    public UslugaController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        usluga();
    }

    private void usluga() {
        try {
            uslugi = FXCollections.observableArrayList();
            String select = "SELECT * FROM `usluga`";
            connection=dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Usluga usluga = new Usluga();
                usluga.setId_uslugi(resultSet.getInt("id_uslugi"));
                usluga.setNazwa_uslugi(resultSet.getString("nazwa_uslugi"));
                usluga.setRodzaj_uslugi(resultSet.getString("rodzaj_uslugi"));
                uslugi.add(usluga);
            }

            uslugaNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_uslugi"));
            uslugaRodzaj.setCellValueFactory(new PropertyValueFactory<>("rodzaj_uslugi"));

            uslugaTab.setItems(uslugi);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
