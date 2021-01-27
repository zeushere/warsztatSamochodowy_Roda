package service.deleteService;

import service.SceneController;
import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class deleteNaprawa implements Initializable {

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

    public void naprawaDelete(ActionEvent actionEvent) throws SQLException {

        try {
            int id = (int) listNaprawa.get(naprawaSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM naprawa WHERE id_naprawy=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {

                naprawaSelect();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Pomyślnie wykonano polecenie!");
                alert.showAndWait();

                listNaprawa.clear();
                naprawaSelect.getItems().clear();

                Stage thisStage = (Stage) naprawaSelect.getScene().getWindow();
                Scene thisScene = naprawaSelect.getScene();

                SceneController sceneController = new SceneController(thisStage, thisScene);

            }
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Nie wybrano naprawy do usunięcia!");
            alert.showAndWait();
        }
    }
}
