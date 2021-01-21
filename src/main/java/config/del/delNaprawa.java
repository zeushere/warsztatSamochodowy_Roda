package config.del;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class delNaprawa implements Initializable {

    @FXML
    private ComboBox wypozyczenieSelect;
    
    private Connection connection;
    ArrayList listWypozyczenie = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wypozyczenieSelect();
    }

    private void wypozyczenieSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT('Klient: ',klient.imie,' '," +
                    "klient.nazwisko," +
                    "', rower: ',egzemplarz.rodzaj,', ',egzemplarz.firma," +
                    "', data wypożyczenia: ',data_wypozyczenia," +
                    "', data oddania: ',data_zwrotu)as wypo " +
                    "FROM wypozyczenie INNER JOIN klient ON wypozyczenie.id_klienta=klient.id_klienta" +
                    " INNER JOIN egzemplarz ON wypozyczenie.id_egzemplarza=egzemplarz.id_egzemplarza";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wypozyczenieSelect.getItems().add(rs.getString("wypo"));
                listWypozyczenie.add(rs.getInt("id_wypozyczenia"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wypozyczenieDel(ActionEvent actionEvent) throws SQLException{
        int id = (int) listWypozyczenie.get(wypozyczenieSelect.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "DELETE FROM wypozyczenie WHERE id_wypozyczenia="+id;
        int ex = connection.createStatement().executeUpdate(query);
        if(ex>0){
            listWypozyczenie.clear();
            wypozyczenieSelect.getItems().clear();
            wypozyczenieSelect();
        }
    }
}
