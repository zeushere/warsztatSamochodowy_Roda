package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addKlient{

    @FXML
    private TextField klientAddImie;
    @FXML
    private TextField klientAddNazwisko;
    @FXML
    private TextField klientAddPesel;

    private Connection connection;

    public void klientAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        if (klientAddPesel.getLength()==11) {
            String query = "INSERT INTO klient(imie, nazwisko, PESEL) VALUES('"
                    +klientAddImie.getText()+"','"
                    +klientAddNazwisko.getText()+"','"
                    +klientAddPesel.getText()+"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute>0) {
                klientAddImie.clear();
                klientAddNazwisko.clear();
                klientAddPesel.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("PESEL musi mieć 11 cyfr");
            alert.showAndWait();
        }
    }

}
