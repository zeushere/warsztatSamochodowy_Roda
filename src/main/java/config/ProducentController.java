package config;

import connect.DbConnect;
import database.Producent;
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

public class ProducentController implements Initializable {

    @FXML
    private TableView<Producent> producentTab;
    @FXML
    private TableColumn<Producent, String> producentImie;
    @FXML
    private TableColumn<Producent, String> producentNazwisko;
    @FXML
    private TableColumn<Producent, String> producentLiczba;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Producent> producents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        producent();
    }

    private void producent() {
        try {
            producents = FXCollections.observableArrayList();
            String select = "SELECT * FROM producent";
            connection = dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Producent producent = new Producent();
                producent.setId_producenta(resultSet.getInt("id_producenta"));
                producent.setImie(resultSet.getString("imie"));
                producent.setNazwisko(resultSet.getString("nazwisko"));
                producent.setLiczba_egzemplarzy(resultSet.getString("liczba_egzemplarzy"));
                producents.add(producent);
            }

            producentImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            producentNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            producentLiczba.setCellValueFactory(new PropertyValueFactory<>("liczba_egzemplarzy"));

            producentTab.setItems(producents);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
