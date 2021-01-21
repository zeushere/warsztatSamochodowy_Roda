package config;

import connect.DbConnect;
import database.Pracownik;
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

public class PracownikController implements Initializable {
    @FXML
    private TableView<Pracownik> pracownikTab;
    @FXML
    private TableColumn<Pracownik, String> pracownikImie;
    @FXML
    private TableColumn<Pracownik, String> pracownikNazwisko;
    @FXML
    private TableColumn<Pracownik, String> pracownikData;
    @FXML
    private TableColumn<Pracownik, String> pracownikWynagrodzenie;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Pracownik> pracowniks;

    public PracownikController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        pracownik();
    }

    private void pracownik() {
        try {
            pracowniks = FXCollections.observableArrayList();
            String select = "SELECT *,CONCAT(wynagrodzenie,' PLN') as wyna FROM pracownik";
            connection=dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()) {
                Pracownik pracownik = new Pracownik();
                pracownik.setId_pracownika(resultSet.getInt("id_pracownika"));
                pracownik.setImie(resultSet.getString("imie"));
                pracownik.setNazwisko(resultSet.getString("nazwisko"));
                pracownik.setData_zatrudnienia(resultSet.getString("data_zatrudnienia"));
                pracownik.setWynagrodzenie(resultSet.getString("wynagrodzenie"));
                pracownik.setWyna(resultSet.getString("wyna"));
                pracowniks.add(pracownik);
            }

            pracownikImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            pracownikNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            pracownikData.setCellValueFactory(new PropertyValueFactory<>("data_zatrudnienia"));
            pracownikWynagrodzenie.setCellValueFactory(new PropertyValueFactory<>("Wyna"));

            pracownikTab.setItems(pracowniks);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
