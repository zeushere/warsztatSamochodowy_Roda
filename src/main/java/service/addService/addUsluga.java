package service.add;

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

public class addUsluga {

    @FXML
    private TextField uslugaAddNazwa;
    @FXML
    private TextField uslugaAddRodzaj;


    private Connection connection;

    public void addUsluga(ActionEvent actionEvent) throws SQLException, IOException {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();
        if (!(uslugaAddNazwa.getText().equals("")) && !(uslugaAddRodzaj.getText().equals("")) ) {
            String query = "INSERT INTO usluga(nazwa_uslugi, rodzaj_uslugi) VALUES('"
                    +uslugaAddNazwa.getText()+"','"
                    +uslugaAddRodzaj.getText()+"')";
            int execute = connection.createStatement().executeUpdate(query);
            if (execute>0) {


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Pomyślnie wykonano polecenie!");
                alert.showAndWait();

                uslugaAddNazwa.clear();
                uslugaAddRodzaj.clear();


                Stage thisStage = (Stage) uslugaAddRodzaj.getScene().getWindow();
                Scene thisScene = uslugaAddRodzaj.getScene();

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
