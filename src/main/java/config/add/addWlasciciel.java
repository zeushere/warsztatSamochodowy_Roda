package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    public void addWlasciciel(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        if (!(wlascicielAddImie.getText().equals("")) && !(wlascicielAddNazwisko.getText().equals("")) && !(wlascicielAddMarkaSamochodu.getText().equals("")) && !(wlascicielAddModelSamochodu.getText().equals(""))) {
            String query = "INSERT INTO wlasciciel(imie_wlasciciela, nazwisko_wlasciciela, marka_samochodu_wlasciciela, model_samochodu_wlasciciela) VALUES('"
                    +wlascicielAddImie.getText()+"','"
                    +wlascicielAddNazwisko.getText()+"','"
                    +wlascicielAddMarkaSamochodu.getText()+"','"
                    +wlascicielAddModelSamochodu.getText() +"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute>0) {
                wlascicielAddImie.clear();
                wlascicielAddNazwisko.clear();
                wlascicielAddMarkaSamochodu.clear();
                wlascicielAddModelSamochodu.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Uzupełnij wszystkie pola!");
            alert.showAndWait();
        }
    }

}