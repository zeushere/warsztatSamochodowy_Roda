package service.update;

import service.SceneController;
import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class updateUsluga implements Initializable {

    @FXML
    private TextField uslugaUpdateNazwa;

    @FXML
    private TextField uslugaUpdateRodzaj;

    @FXML
    private Button updateUslugaButton;

    @FXML
    private ComboBox selectUsluga;

    @FXML
    private CheckBox updateCheckboxNazwaUslugi;

    @FXML
    private CheckBox updateCheckboxRodzajUslugi;

    private Connection connection;

    private ArrayList listUsluga = new ArrayList();

    int czyWykonane = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uslugaUpdateNazwa.setDisable(true);
        uslugaUpdateRodzaj.setDisable(true);
        updateUslugaButton.setDisable(true);

        selectUsluga();

    }


    private void selectUsluga() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(nazwa_uslugi,' | ',rodzaj_uslugi)as ImNa FROM usluga";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectUsluga.getItems().add(rs.getString("ImNa"));
                listUsluga.add(rs.getInt("id_uslugi"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectUslugaAction(ActionEvent actionEvent) throws SQLException{

        if(selectUsluga.getSelectionModel().isEmpty() == false)
        {
            updateUslugaButton.setDisable(false);
        }
    }


    public void checkBoxNazwaUslugi(ActionEvent actionEvent) throws SQLException{

        if (uslugaUpdateNazwa.isDisable() == true) {
            uslugaUpdateNazwa.setDisable(false);
        } else {
            uslugaUpdateNazwa.setDisable(true);
        }
    }


    public void checkBoxRodzajUslugi(ActionEvent actionEvent) throws SQLException{

        if (uslugaUpdateRodzaj.isDisable() == true) {
            uslugaUpdateRodzaj.setDisable(false);
        } else {
            uslugaUpdateRodzaj.setDisable(true);
        }

    }
    public void updateUsluga(ActionEvent actionEvent) throws SQLException, IOException {


        int idUslugi = (int) listUsluga.get(selectUsluga.getSelectionModel().getSelectedIndex());


        if (uslugaUpdateNazwa.isDisable() == false || uslugaUpdateRodzaj.isDisable() == false)

        {
            if (uslugaUpdateNazwa.isDisable() == false)

            {


                if(uslugaUpdateNazwa.getText().equals("") || uslugaUpdateNazwa.getText().charAt(0) == ' ')
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd!");
                    alert.setHeaderText("Nie wpisano poprawnie nazwy usługi!");
                    alert.showAndWait();
                }

                else {
                    DbConnect dbConnect = new DbConnect();
                    connection = dbConnect.getConnection();


                    Statement zmiana = connection.createStatement();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UPDATE usluga SET nazwa_uslugi = '");
                    stringBuilder.append(uslugaUpdateNazwa.getText());
                    stringBuilder.append("'");
                    stringBuilder.append("WHERE id_uslugi=");
                    stringBuilder.append(idUslugi);

                    int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                    if(rowsCount>0)
                    {
                        uslugaUpdateNazwa.clear();

                        czyWykonane = 1;
                    }
                }
            }

            if (uslugaUpdateRodzaj.isDisable() == false) {

                if(uslugaUpdateRodzaj.getText().equals("") || uslugaUpdateRodzaj.getText().charAt(0) == ' ')
                {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd!");
                    alert.setHeaderText("Nie wpisano poprawnie rodzaju usługi!");
                    alert.showAndWait();
                }

                else
                {
                    DbConnect dbConnect = new DbConnect();
                    connection = dbConnect.getConnection();


                    Statement zmiana = connection.createStatement();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UPDATE usluga SET rodzaj_uslugi = '");
                    stringBuilder.append(uslugaUpdateRodzaj.getText());
                    stringBuilder.append("'");
                    stringBuilder.append("WHERE id_uslugi=");
                    stringBuilder.append(idUslugi);

                    int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                    if(rowsCount>0)
                    {
                        uslugaUpdateRodzaj.clear();

                        czyWykonane = 1;

                    }
                }
            }

            if(czyWykonane == 1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Pomyślnie wykonano polecenie!");
                alert.showAndWait();


                Stage thisStage = (Stage) uslugaUpdateRodzaj.getScene().getWindow();
                Scene thisScene = uslugaUpdateRodzaj.getScene();

                SceneController sceneController = new SceneController(thisStage, thisScene);
            }


        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Nie wybrano nic do zmiany!");
            alert.showAndWait();

        }

    }
}

