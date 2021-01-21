package config.add;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addEgzemplarz implements Initializable {

    @FXML
    private TextField egzemplarzAddRodzaj;
    @FXML
    private TextField egzemplarzAddFirma;
    @FXML
    private DatePicker egzemplarzAddData;
    @FXML
    private ComboBox egzemplarzAddProducent;

    private Connection connection;
    ArrayList listProducent = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectProducent();
    }

    private void selectProducent() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie,' ',nazwisko)as ImNa FROM producent";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                egzemplarzAddProducent.getItems().add(rs.getString("ImNa"));
                listProducent.add(rs.getInt("id_producenta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void egzemplarzAdd(ActionEvent actionEvent) throws SQLException {
        int idProducenta = (int) listProducent.get(egzemplarzAddProducent.getSelectionModel().getSelectedIndex());
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        String query = "INSERT INTO egzemplarz(rodzaj, firma, data_produkcji, id_producenta) VALUES('"
                +egzemplarzAddRodzaj.getText()+"','"
                +egzemplarzAddFirma.getText()+"','"
                +egzemplarzAddData.getValue()+"','"
                +idProducenta+"')";

        String query2 = "UPDATE producent SET liczba_egzemplarzy = liczba_egzemplarzy + 1 WHERE id_producenta="+idProducenta;

        int execute = connection.createStatement().executeUpdate(query);
        connection.createStatement().executeUpdate(query2);

        if (execute > 0){
            egzemplarzAddRodzaj.clear();
            egzemplarzAddFirma.clear();
            egzemplarzAddData.setValue(null);
            egzemplarzAddProducent.getItems().clear();
            listProducent.clear();
            selectProducent();
        }
    }


}
