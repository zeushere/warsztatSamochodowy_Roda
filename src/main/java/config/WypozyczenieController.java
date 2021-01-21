package config;

import connect.DbConnect;
import database.Wypozyczenie;
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

public class WypozyczenieController implements Initializable {
    @FXML
    private TableView<Wypozyczenie> wypozyczenieTab;
    @FXML
    private TableColumn<Wypozyczenie, String> wypozyczenieKlient;
    @FXML
    private TableColumn<Wypozyczenie, String> wypozyczenieEgzemplarz;
    @FXML
    private TableColumn<Wypozyczenie, String> wypozyczeniePracownik;
    @FXML
    private TableColumn<Wypozyczenie, String> wypozyczenieDataWyp;
    @FXML
    private TableColumn<Wypozyczenie, String> wypozyczenieDataZw;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Wypozyczenie> wypozyczenies;

    public WypozyczenieController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        wypozyczenia();
    }

    private void wypozyczenia() {
        try {
            wypozyczenies = FXCollections.observableArrayList();
            String select = "SELECT *, CONCAT(klient.imie,' ',klient.nazwisko) as kimna, CONCAT(egzemplarz.rodzaj,', ',egzemplarz.firma) as egzemplarz, CONCAT(pracownik.imie,' ',pracownik.nazwisko) as pimna, data_wypozyczenia, data_zwrotu FROM wypozyczenie INNER JOIN klient ON wypozyczenie.id_klienta=klient.id_klienta INNER JOIN egzemplarz ON wypozyczenie.id_egzemplarza=egzemplarz.id_egzemplarza INNER JOIN pracownik ON wypozyczenie.id_pracownika=pracownik.id_pracownika";
            connection = dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Wypozyczenie wypozyczenie = new Wypozyczenie();
                wypozyczenie.setId_wypozyczenia(resultSet.getInt("id_wypozyczenia"));
                wypozyczenie.setId_klienta(resultSet.getString("id_klienta"));
                wypozyczenie.setKImNa(resultSet.getString("kimna"));
                wypozyczenie.setId_egzemplarza(resultSet.getString("id_egzemplarza"));
                wypozyczenie.setEgzemplarz(resultSet.getString("egzemplarz"));
                wypozyczenie.setId_pracownika(resultSet.getString("id_pracownika"));
                wypozyczenie.setPImNa(resultSet.getString("pimna"));
                wypozyczenie.setData_wypozyczenia(resultSet.getString("data_wypozyczenia"));
                wypozyczenie.setData_zwrotu(resultSet.getString("data_zwrotu"));
                wypozyczenies.add(wypozyczenie);
            }

            wypozyczenieKlient.setCellValueFactory(new PropertyValueFactory<>("KImNa"));
            wypozyczenieEgzemplarz.setCellValueFactory(new PropertyValueFactory<>("Egzemplarz"));
            wypozyczeniePracownik.setCellValueFactory(new PropertyValueFactory<>("PImNa"));
            wypozyczenieDataWyp.setCellValueFactory(new PropertyValueFactory<>("data_wypozyczenia"));
            wypozyczenieDataZw.setCellValueFactory(new PropertyValueFactory<>("data_zwrotu"));

            wypozyczenieTab.setItems(wypozyczenies);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
