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

public class delProducent implements Initializable {

    @FXML
    private ComboBox producentSelect;

    private Connection connection;
    ArrayList listProducent = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        producentSelect();
    }

    private void producentSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM producent";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                producentSelect.getItems().add(rs.getString("ImNa"));
                listProducent.add(rs.getInt("id_producenta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delProducent(ActionEvent actionEvent){
        try {
            int id = (int) listProducent.get(producentSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM producent WHERE id_producenta=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listProducent.clear();
                producentSelect.getItems().clear();
                producentSelect();
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usnąć egzemplarz przypisany do producenta!");
            alert.showAndWait();
        }
    }
}
