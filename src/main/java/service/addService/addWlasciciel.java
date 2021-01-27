package service.addService;

import service.SceneController;
import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class addWlasciciel {

    @FXML
    private TextField wlascicielAddImie;
    @FXML
    private TextField wlascicielAddNazwisko;
    @FXML
    private TextField wlascicielAddMarkaSamochodu;
    @FXML
    private TextField wlascicielAddModelSamochodu;


    private Connection connection;

    public void addWlasciciel(ActionEvent actionEvent) throws SQLException, IOException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        if (!(wlascicielAddImie.getText().equals("")) && !(wlascicielAddNazwisko.getText().equals("")) && !(wlascicielAddMarkaSamochodu.getText().equals("")) && !(wlascicielAddModelSamochodu.getText().equals(""))) {
            String query = "INSERT INTO wlasciciel(imie_wlasciciela, nazwisko_wlasciciela, marka_samochodu_wlasciciela, model_samochodu_wlasciciela) VALUES('"
                    + wlascicielAddImie.getText() + "','"
                    + wlascicielAddNazwisko.getText() + "','"
                    + wlascicielAddMarkaSamochodu.getText() + "','"
                    + wlascicielAddModelSamochodu.getText() + "')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Pomyślnie wykonano polecenie!");
                alert.showAndWait();

                wlascicielAddImie.clear();
                wlascicielAddNazwisko.clear();
                wlascicielAddMarkaSamochodu.clear();
                wlascicielAddModelSamochodu.clear();

                Stage thisStage = (Stage) wlascicielAddModelSamochodu.getScene().getWindow();
                Scene thisScene = wlascicielAddModelSamochodu.getScene();

                SceneController sceneController = new SceneController(thisStage, thisScene);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Uzupełnij wszystkie pola!");
            alert.showAndWait();
        }
    }

}