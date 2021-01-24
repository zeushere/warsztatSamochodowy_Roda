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

public class delUsluga implements Initializable {

    @FXML
    private ComboBox uslugaSelect;

    private Connection connection;
    ArrayList listUsluga = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uslugaSelect();
    }

    private void uslugaSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(nazwa_uslugi,' | ',rodzaj_uslugi)as ImNa FROM usluga";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                uslugaSelect.getItems().add(rs.getString("ImNa"));
                listUsluga.add(rs.getInt("id_uslugi"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void uslugaDel(ActionEvent actionEvent) {
        try {
            int id = (int) listUsluga.get(uslugaSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM usluga WHERE id_uslugi=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {
                listUsluga.clear();
                uslugaSelect.getItems().clear();
                uslugaSelect();
            }
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usunąć naprawę powiązaną z daną usługą!");
            alert.showAndWait();
        }

        catch (RuntimeException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Nie wybrano usługi do usunięcia!");
            alert.showAndWait();
        }

    }


}
