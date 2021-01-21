package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class addPracownik {

    @FXML
    private TextField pracownikAddImie;
    @FXML
    private TextField pracownikAddNazwisko;
    @FXML
    private DatePicker pracownikAddData;
    @FXML
    private TextField pracownikAddWynagrodzenie;

    private Connection connection;

    public void pracownikAdd(ActionEvent actionEvent) throws SQLException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO pracownik(imie, nazwisko, data_zatrudnienia, wynagrodzenie)VALUES " +
                "('"+pracownikAddImie.getText()+"','"
                +pracownikAddNazwisko.getText()+"','"
                +pracownikAddData.getValue()+"','"
                +pracownikAddWynagrodzenie.getText()+"')";

        int execute = connection.createStatement().executeUpdate(query);
        if (execute > 0) {
            pracownikAddImie.clear();
            pracownikAddNazwisko.clear();
            pracownikAddData.setValue(null);
            pracownikAddWynagrodzenie.clear();
        }
    }
}
