package config;

import connect.DbConnect;
import database.Egzemplarz;
import database.Producent;
import database.Wlasciciel;
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

public class WlascicielController implements Initializable {
    @FXML
    private TableView<Wlasciciel> wlascicielTab;
    @FXML
    private TableColumn<Wlasciciel, String> wlascicielImie;
    @FXML
    private TableColumn<Wlasciciel, String> wlascicielNazwisko;
    @FXML
    private TableColumn<Wlasciciel, String> wlascicielMarkaSamochodu;
    @FXML
    private TableColumn<Wlasciciel, String> wlascicielModelSamochodu;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Wlasciciel> wlasciciele;

    public WlascicielController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        wlasciciel();
    }

    private void wlasciciel() {
        try {
            wlasciciele = FXCollections.observableArrayList();
            String select = "SELECT * FROM wlasciciel";
            connection = dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Wlasciciel wlasciciel = new Wlasciciel();
                wlasciciel.setId_wlasciciela(resultSet.getInt("id_wlasciciela"));
                wlasciciel.setImie_wlasciciela(resultSet.getString("imie_wlasciciela"));
                wlasciciel.setNazwisko_wlasciciela(resultSet.getString("nazwisko_wlasciciela"));
                wlasciciel.setMarka_samochodu_wlasciciela(resultSet.getString("marka_samochodu_wlasciciela"));
                wlasciciel.setModel_samochodu_wlasciciela(resultSet.getString("model_samochodu_wlasciciela"));
                wlasciciele.add(wlasciciel);
            }

            wlascicielImie.setCellValueFactory(new PropertyValueFactory<>("imie_wlasciciela"));
            wlascicielNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko_wlasciciela"));
            wlascicielMarkaSamochodu.setCellValueFactory(new PropertyValueFactory<>("marka_samochodu_wlasciciela"));
            wlascicielModelSamochodu.setCellValueFactory(new PropertyValueFactory<>("model_samochodu_wlasciciela"));

            wlascicielTab.setItems(wlasciciele);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
