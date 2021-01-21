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
    private ComboBox naprawaSelect;
    
    private Connection connection;
    ArrayList listNaprawa = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        naprawaSelect();
    }

    private void naprawaSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT('Wlasciciel: ',wlasciciel.imie_wlasciciela,' '," +
                    "wlasciciel.nazwisko_wlasciciela," +
                    "', Usługa: ',usluga.nazwa_uslugi,', ',usluga.rodzaj_uslugi," +
                    "', Koszt naprawy: ',koszt_naprawy,'zł' "  +
                    "', Data naprawy: ',data_naprawy)as naprawa " +
                    "FROM naprawa INNER JOIN wlasciciel ON naprawa.id_wlasciciela=wlasciciel.id_wlasciciela" +
                    " INNER JOIN usluga ON naprawa.id_uslugi=usluga.id_uslugi";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                naprawaSelect.getItems().add(rs.getString("naprawa"));
                listNaprawa.add(rs.getInt("id_naprawy"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void naprawaDelete(ActionEvent actionEvent) throws SQLException{
        int id = (int) listNaprawa.get(naprawaSelect.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "DELETE FROM naprawa WHERE id_naprawy="+id;
        int ex = connection.createStatement().executeUpdate(query);
        if(ex>0){
            listNaprawa.clear();
            naprawaSelect.getItems().clear();
            naprawaSelect();
        }
    }
}
