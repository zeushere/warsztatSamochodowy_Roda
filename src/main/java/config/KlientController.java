package config;

import connect.DbConnect;
import database.Klient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KlientController implements Initializable {

    @FXML
    private TableView<Klient> klientTab;
    @FXML
    private TableColumn<Klient, String> klientImie;
    @FXML
    private TableColumn<Klient, String> klientNazwisko;
    @FXML
    private TableColumn<Klient, String> klientPESEL;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Klient> klients;

    public KlientController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        klient();
    }

    private void klient() {
        try {
            klients = FXCollections.observableArrayList();
            String select = "SELECT * FROM klient";
            connection=dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Klient klient = new Klient();
                klient.setId_klienta(resultSet.getInt("id_klienta"));
                klient.setImie(resultSet.getString("imie"));
                klient.setNazwisko(resultSet.getString("nazwisko"));
                klient.setPESEL(resultSet.getString("PESEL"));
                klients.add(klient);
            }

            klientImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
            klientNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
            klientPESEL.setCellValueFactory(new PropertyValueFactory<>("PESEL"));

            klientTab.setItems(klients);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
