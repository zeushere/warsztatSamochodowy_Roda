package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class addProducent {

    @FXML
    private TextField producentAddImie;
    @FXML
    private TextField producentAddNazwisko;

    private Connection connection;

    public void producentAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO producent(imie, nazwisko, liczba_egzemplarzy) VALUES('"
                +producentAddImie.getText()+"','"
                +producentAddNazwisko.getText()+"',"
                +"'0')";
        int execute = connection.createStatement().executeUpdate(query);

        if (execute > 0){
            producentAddImie.clear();
            producentAddNazwisko.clear();
        }
    }
}
