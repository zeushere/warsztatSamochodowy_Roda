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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class deleteWlasciciel implements Initializable {

    @FXML
    private ComboBox wlascicielSelect;

    private Connection connection;
    ArrayList listWlasciciel = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) { wlascicielSelect(); }

    private void wlascicielSelect() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie_wlasciciela,' ',nazwisko_wlasciciela,' ',marka_samochodu_wlasciciela,' ',model_samochodu_wlasciciela)as ImNa FROM wlasciciel";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                wlascicielSelect.getItems().add(rs.getString("ImNa"));
                listWlasciciel.add(rs.getInt("id_wlasciciela"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void wlascicielDel(ActionEvent actionEvent) throws IOException {
        try {
            int id = (int) listWlasciciel.get(wlascicielSelect.getSelectionModel().getSelectedIndex());
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "DELETE FROM wlasciciel WHERE id_wlasciciela=" + id;
            int ex = connection.createStatement().executeUpdate(query);
            if (ex > 0) {


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Pomyślnie wykonano polecenie!");
                alert.showAndWait();

                listWlasciciel.clear();
                wlascicielSelect.getItems().clear();
                wlascicielSelect();

                Stage thisStage = (Stage) wlascicielSelect.getScene().getWindow();
                Scene thisScene = wlascicielSelect.getScene();

                SceneController sceneController = new SceneController(thisStage, thisScene);

            }
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Musisz najpierw usunąć naprawę powiązaną z danym właścicielem!");
            alert.showAndWait();
        }

        catch (RuntimeException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Nie wybrano właściciela do usunięcia!");
            alert.showAndWait();
        }
    }


}
