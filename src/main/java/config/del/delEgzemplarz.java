package config.del;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class delEgzemplarz implements Initializable {

    @FXML
    private ComboBox egzemplarzSelect;

    private Connection connection;
    ArrayList listEgzemplarz = new ArrayList();
    ArrayList listProducent = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        egzemplarzSelect();
    }

    private void egzemplarzSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(rodzaj,', ',firma,', ',data_produkcji)as rower FROM egzemplarz";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                egzemplarzSelect.getItems().add(rs.getString("rower"));
                listEgzemplarz.add(rs.getInt("id_egzemplarza"));
                listProducent.add(rs.getInt("id_producenta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void egzemplarzDel(ActionEvent actionEvent) {
        try {
            int id = (int) listEgzemplarz.get(egzemplarzSelect.getSelectionModel().getSelectedIndex());
            int idProducenta = (int) listProducent.get(egzemplarzSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM egzemplarz WHERE id_egzemplarza=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            String query2 = "UPDATE producent SET liczba_egzemplarzy = liczba_egzemplarzy - 1 WHERE id_producenta=" + idProducenta;
            connection.createStatement().executeUpdate(query2);

            if (ex > 0) {
                listEgzemplarz.clear();
                egzemplarzSelect.getItems().clear();
                egzemplarzSelect();
            }
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć wypożyczenie z danym egzemplarzem!");
            alert.showAndWait();
        }
    }
}
