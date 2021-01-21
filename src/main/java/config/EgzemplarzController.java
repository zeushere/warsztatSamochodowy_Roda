package config;

import connect.DbConnect;
import database.Egzemplarz;
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

public class EgzemplarzController implements Initializable {
    @FXML
    private TableView<Egzemplarz> egzemplarzTab;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzRodzaj;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzFirma;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzData;
    @FXML
    private TableColumn<Egzemplarz, String> egzemplarzPracownik;

    private Connection connection;
    private DbConnect dbConnect;
    private ObservableList<Egzemplarz> egzemplarzs;

    public EgzemplarzController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnect = new DbConnect();

        egzemplarz();
    }

    private void egzemplarz() {
        try {
            egzemplarzs = FXCollections.observableArrayList();
            String select = "SELECT *, CONCAT(producent.imie,' ', producent.nazwisko) as pimna FROM egzemplarz INNER JOIN producent ON egzemplarz.id_producenta=producent.id_producenta";
            connection = dbConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()){
                Egzemplarz egzemplarz = new Egzemplarz();
                egzemplarz.setId_egzemplarza(resultSet.getInt("id_egzemplarza"));
                egzemplarz.setRodzaj(resultSet.getString("rodzaj"));
                egzemplarz.setFirma(resultSet.getString("firma"));
                egzemplarz.setData_produkcji(resultSet.getString("data_produkcji"));
                egzemplarz.setPImNa(resultSet.getString("pimna"));
                egzemplarzs.add(egzemplarz);
            }

            egzemplarzRodzaj.setCellValueFactory(new PropertyValueFactory<>("rodzaj"));
            egzemplarzFirma.setCellValueFactory(new PropertyValueFactory<>("firma"));
            egzemplarzData.setCellValueFactory(new PropertyValueFactory<>("data_produkcji"));
            egzemplarzPracownik.setCellValueFactory(new PropertyValueFactory<>("PImNa"));

            egzemplarzTab.setItems(egzemplarzs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
