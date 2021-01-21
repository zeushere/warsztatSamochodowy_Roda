package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addNaprawa implements Initializable {
    
    @FXML
    private ComboBox naprawaSelectWlasciciel;
    @FXML
    private ComboBox naprawaSelectUsluga;
    @FXML
    private TextField naprawaKoszt;
    @FXML
    private DatePicker naprawaData;

    private Connection connection;
    private ArrayList listWlasciciel = new ArrayList();
    private ArrayList listUsluga = new ArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        naprawaSelectWlasciciel();
        naprawaSelectUsluga();


    }

    private void naprawaSelectWlasciciel() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie_wlasciciela,' ',nazwisko_wlasciciela)as ImNa FROM wlasciciel";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                naprawaSelectWlasciciel.getItems().add(rs.getString("ImNa"));
                listWlasciciel.add(rs.getInt("id_wlasciciela"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void naprawaSelectUsluga() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(nazwa_uslugi,' | ',rodzaj_uslugi)as naro FROM usluga";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                naprawaSelectUsluga.getItems().add(rs.getString("naro"));
                listUsluga.add(rs.getInt("id_uslugi"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void naprawaAdd(ActionEvent actionEvent) throws SQLException{
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        int idWlasciciela = (int) listWlasciciel.get(naprawaSelectWlasciciel.getSelectionModel().getSelectedIndex());
        int idUslugi = (int) listUsluga.get(naprawaSelectUsluga.getSelectionModel().getSelectedIndex());
        LocalDate data = LocalDate.now();
        LocalDate dataNaprawy = naprawaData.getValue();

        if ((data.isBefore(dataNaprawy)) || data.isEqual(dataNaprawy)){
            System.out.println(naprawaKoszt.getText());
            String query = "INSERT INTO naprawa(koszt_naprawy,data_naprawy,id_wlasciciela,id_uslugi) VALUES ('"
                    +naprawaKoszt.getText()+"','"
                    +dataNaprawy+"','"
                    +idWlasciciela+"','"
                    +idUslugi +"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute > 0) {
                naprawaSelectWlasciciel.getItems().clear();
                listWlasciciel.clear();
                naprawaSelectWlasciciel();
                naprawaSelectUsluga.getItems().clear();
                listUsluga.clear();
                naprawaSelectUsluga();
                naprawaData.setValue(null);
                naprawaKoszt.clear();

            }
        } else {
            System.out.println("elo");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Ustaw poprawnie datę oddania (nie może być wcześniejsza niż aktualna)");
            alert.showAndWait();
        }
    }

    
}
