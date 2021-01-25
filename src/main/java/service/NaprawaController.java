package service;

import connect.DbConnect;
import database.Naprawa;
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

public class NaprawaController implements Initializable {
    @FXML
    private TableView<Naprawa> naprawaTable;
    @FXML
    private TableColumn<Naprawa, String> naprawaNazwaUslugi;
    @FXML
    private TableColumn<Naprawa, String> naprawaDataNaprawy;
    @FXML
    private TableColumn<Naprawa, String> naprawaKosztNaprawy;
    @FXML
    private TableColumn<Naprawa, String> naprawaWlasciciel;
    @FXML
    private TableColumn<Naprawa, String> naprawaSamochod;
    @FXML
    private TextField filterField;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Naprawa> naprawy;

    public NaprawaController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        naprawa();
    }

    private void naprawa() {
        try {
            naprawy = FXCollections.observableArrayList();
            String select = "SELECT *, CONCAT(usluga.nazwa_uslugi) as un, " +
                    "naprawa.data_naprawy, CONCAT(naprawa.koszt_naprawy, ' zł') as kosztPLN, " +
                    "CONCAT(wlasciciel.imie_wlasciciela,' ',wlasciciel.nazwisko_wlasciciela) as wimnw, " +
                    "CONCAT(wlasciciel.marka_samochodu_wlasciciela,' ',wlasciciel.model_samochodu_wlasciciela) as wmamo " +
                    "FROM naprawa INNER JOIN usluga ON naprawa.id_uslugi=usluga.id_uslugi " +
                    "INNER JOIN wlasciciel ON naprawa.id_wlasciciela=wlasciciel.id_wlasciciela";
            connection = dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Naprawa naprawa = new Naprawa();
                naprawa.setId_naprawy(resultSet.getInt("id_naprawy"));
                naprawa.setId_wlasciciela(resultSet.getString("id_wlasciciela"));
                naprawa.setUn(resultSet.getString("un"));
                naprawa.setData_naprawy(resultSet.getString("data_naprawy"));
                naprawa.setKosztPLN(resultSet.getString("kosztPLN"));
                naprawa.setUn(resultSet.getString("un"));
                naprawa.setWimnw(resultSet.getString("wimnw"));
                naprawa.setWmamo(resultSet.getString("wmamo"));
                naprawy.add(naprawa);
            }

            naprawaNazwaUslugi.setCellValueFactory(new PropertyValueFactory<>("un"));
            naprawaDataNaprawy.setCellValueFactory(new PropertyValueFactory<>("data_naprawy"));
            naprawaKosztNaprawy.setCellValueFactory(new PropertyValueFactory<>("kosztPLN"));
            naprawaWlasciciel.setCellValueFactory(new PropertyValueFactory<>("wimnw"));
            naprawaSamochod.setCellValueFactory(new PropertyValueFactory<>("wmamo"));

            FilteredList<Naprawa> filteredData = new FilteredList<>(naprawy, b-> true);

            filterField.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredData.setPredicate(naprawa->{
                    if(newValue == null || newValue.isEmpty())
                    {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(naprawa.getWmamo().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if(naprawa.getWimnw().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if(naprawa.getUn().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if(naprawa.getData_naprawy().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if(naprawa.getWimnw().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }
                    else if(naprawa.getKosztPLN().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    {
                        return true;
                    }

                    else {
                        return false;
                    }
                });


            }));

            SortedList<Naprawa> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(naprawaTable.comparatorProperty());

            naprawaTable.setItems(sortedData);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}