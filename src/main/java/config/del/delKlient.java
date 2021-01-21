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

public class delKlient implements Initializable {

    @FXML
    private ComboBox klientSelect;

    private Connection connection;
    ArrayList listKlient = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        klientSelect();
    }

    private void klientSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM klient";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                klientSelect.getItems().add(rs.getString("ImNa"));
                listKlient.add(rs.getInt("id_klienta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void klientDel(ActionEvent actionEvent) {
        try {
            int id = (int) listKlient.get(klientSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM klient WHERE id_klienta=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listKlient.clear();
                klientSelect.getItems().clear();
                klientSelect();
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć wypożyczenie z danym klientem!");
            alert.showAndWait();
        }
    }


}
