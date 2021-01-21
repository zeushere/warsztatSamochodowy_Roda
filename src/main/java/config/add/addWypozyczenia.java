package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addWypozyczenia implements Initializable {
    
    @FXML
    private ComboBox wypozyczeniaSelectKlient;
    @FXML
    private ComboBox wypozyczeniaSelectEgzemplarz;
    @FXML
    private ComboBox wypozyczeniaSelectPracownik;
    @FXML
    private DatePicker wypozyczeniaData;

    private Connection connection;
    private ArrayList listKlient = new ArrayList();
    private ArrayList listEgzemplarz = new ArrayList();
    private ArrayList listPracownik = new ArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypozyczeniaSelectKlient();
        wypozyczeniaSelectEgzemplarz();
        wypozyczeniaSelectPracownik();
    }

    private void wypozyczeniaSelectPracownik() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM pracownik";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelectPracownik.getItems().add(rs.getString("ImNa"));
                listPracownik.add(rs.getInt("id_pracownika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void wypozyczeniaSelectEgzemplarz() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(rodzaj,', ',firma,', ',data_produkcji)as rower FROM egzemplarz";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelectEgzemplarz.getItems().add(rs.getString("rower"));
                listEgzemplarz.add(rs.getInt("id_egzemplarza"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void wypozyczeniaSelectKlient() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM klient";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczeniaSelectKlient.getItems().add(rs.getString("ImNa"));
                listKlient.add(rs.getInt("id_klienta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void naprawaAdd(ActionEvent actionEvent) throws SQLException{
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        int idKlienta = (int) listKlient.get(wypozyczeniaSelectKlient.getSelectionModel().getSelectedIndex());
        int idEgzemplarza = (int) listEgzemplarz.get(wypozyczeniaSelectEgzemplarz.getSelectionModel().getSelectedIndex());
        int idPracownika = (int) listPracownik.get(wypozyczeniaSelectPracownik.getSelectionModel().getSelectedIndex());
        LocalDate data = LocalDate.now();
        LocalDate dataOdd = wypozyczeniaData.getValue();
        if (data.isBefore(dataOdd) || data.isEqual(dataOdd)) {
            String query = "INSERT INTO wypozyczenie(id_klienta,id_egzemplarza,id_pracownika,data_wypozyczenia,data_zwrotu) VALUES ('"
                    +idKlienta+"','"
                    +idEgzemplarza+"','"
                    +idPracownika+"','"
                    +data+"','"
                    +dataOdd+"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute > 0) {
                wypozyczeniaSelectKlient.getItems().clear();
                listKlient.clear();
                wypozyczeniaSelectKlient();
                wypozyczeniaSelectPracownik.getItems().clear();
                listPracownik.clear();
                wypozyczeniaSelectPracownik();
                wypozyczeniaSelectEgzemplarz.getItems().clear();
                listEgzemplarz.clear();
                wypozyczeniaSelectEgzemplarz();
                wypozyczeniaData.setValue(null);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Ustaw poprawnie datę oddania (nie może być wcześniejsza niż aktualna)");
            alert.showAndWait();
        }
    }

    
}
