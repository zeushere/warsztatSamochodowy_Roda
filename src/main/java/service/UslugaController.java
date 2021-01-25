package service;

import connect.DbConnect;
import database.Usluga;
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

public class UslugaController implements Initializable {

    @FXML
    private TableView<Usluga> uslugaTable;
    @FXML
    private TableColumn<Usluga, String> uslugaNazwa;
    @FXML
    private TableColumn<Usluga, String> uslugaRodzaj;
    @FXML
    private TextField filterField;


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

            FilteredList<Usluga> filteredData = new FilteredList<>(uslugi, b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(usluga -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (usluga.getNazwa_uslugi().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    }
                     else if (usluga.getRodzaj_uslugi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                        else {
                        return false;
                    }
                });
            });

            SortedList<Usluga> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(uslugaTable.comparatorProperty());

            uslugaTable.setItems(sortedData);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
