package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class addUsluga {

    @FXML
    private TextField uslugaAddNazwa;
    @FXML
    private TextField uslugaAddRodzaj;


    private Connection connection;

    public void addUsluga(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        if (!(uslugaAddNazwa.getText().equals("")) && !(uslugaAddRodzaj.getText().equals("")) ) {
            String query = "INSERT INTO usluga(nazwa_uslugi, rodzaj_uslugi) VALUES('"
                    +uslugaAddNazwa.getText()+"','"
                    +uslugaAddRodzaj.getText()+"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute>0) {
                uslugaAddNazwa.clear();
                uslugaAddRodzaj.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Uzupełnij wszystkie pola!");
            alert.showAndWait();
        }
    }

}
