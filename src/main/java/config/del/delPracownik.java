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

public class delPracownik implements Initializable {

    @FXML
    private ComboBox pracownikSelect;

    private Connection connection;
    ArrayList listPracownik = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pracownikSelect();
    }

    private void pracownikSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM pracownik";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                pracownikSelect.getItems().add(rs.getString("ImNa"));
                listPracownik.add(rs.getInt("id_pracownika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void pracownikDel(ActionEvent actionEvent){
        try {
            int id = (int) listPracownik.get(pracownikSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM pracownik WHERE id_pracownika=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listPracownik.clear();
                pracownikSelect.getItems().clear();
                pracownikSelect();
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć wypożyczenie z danym pracownikiem!");
            alert.showAndWait();
        }
    }


}
