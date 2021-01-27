package service;

import connect.DbConnect;
import database.Wlasciciel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WlascicielController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Wlasciciel> wlascicielTable;
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
            while (resultSet.next()) {
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


            FilteredList<Wlasciciel> filteredData = new FilteredList<>(wlasciciele, b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(wlasciciel -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (wlasciciel.getImie_wlasciciela().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (wlasciciel.getNazwisko_wlasciciela().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (wlasciciel.getMarka_samochodu_wlasciciela().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (wlasciciel.getModel_samochodu_wlasciciela().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;
                });
            });


            SortedList<Wlasciciel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(wlascicielTable.comparatorProperty());


            wlascicielTable.setItems(sortedData);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

