package config.update;

import config.MainController;
import config.NaprawaController;
import config.SceneController;
import config.WlascicielController;
import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import jdk.jfr.internal.tool.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class updateWlasciciel implements Initializable {

    @FXML
    private ComboBox selectWlasciciel;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private TextField wlascicielUpdateImie;
    @FXML
    private CheckBox updateChecboxImieWlasciciela;


    @FXML
    private TextField wlascicielUpdateNazwisko;
    @FXML
    private CheckBox updateChecboxNazwiskoWlasciciela;

    @FXML
    private TextField wlascicielUpdateMarkaSamochodu;
    @FXML
    private CheckBox updateChecboxMarkaSamochoduWlasciciela;

    @FXML
    private TextField wlascicielUpdateModelSamochodu;
    @FXML
    private CheckBox updateChecboxModelSamochoduWlasciciela;

    @FXML
    private Button updateWlascicielButton;

    private Connection connection;

    private ArrayList listWlasciciel = new ArrayList();

    int czyWykonane = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wlascicielUpdateImie.setDisable(true);
        wlascicielUpdateNazwisko.setDisable(true);
        wlascicielUpdateMarkaSamochodu.setDisable((true));
        wlascicielUpdateModelSamochodu.setDisable(true);
        updateWlascicielButton.setDisable(true);

        selectWlasciciel();

    }


    private void selectWlasciciel() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT(imie_wlasciciela,' ',nazwisko_wlasciciela,' ',marka_samochodu_wlasciciela,' ',model_samochodu_wlasciciela)as ImNa FROM wlasciciel";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectWlasciciel.getItems().add(rs.getString("ImNa"));
                listWlasciciel.add(rs.getInt("id_wlasciciela"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectWlascicielAction(ActionEvent actionEvent) throws SQLException{

        if(selectWlasciciel.getSelectionModel().isEmpty() == false)
        {
            updateWlascicielButton.setDisable(false);
        }
    }


    public void checkBoxImieWlasciciela(ActionEvent actionEvent) throws SQLException {

        if (wlascicielUpdateImie.isDisable() == true) {
            wlascicielUpdateImie.setDisable(false);
        } else {
            wlascicielUpdateImie.setDisable(true);
        }

    }

    public void checkBoxNazwiskoWlasciciela(ActionEvent actionEvent) throws SQLException {

        if (wlascicielUpdateNazwisko.isDisable() == true) {
            wlascicielUpdateNazwisko.setDisable(false);
        } else {
            wlascicielUpdateNazwisko.setDisable(true);
        }

    }

    public void checkBoxMarkaSamochoduWlasciciela(ActionEvent actionEvent) throws SQLException {

        if (wlascicielUpdateMarkaSamochodu.isDisable() == true) {
            wlascicielUpdateMarkaSamochodu.setDisable(false);
        } else {
            wlascicielUpdateMarkaSamochodu.setDisable(true);
        }

    }

    public void checkBoxModelSamochoduWlasciciela(ActionEvent actionEvent) throws SQLException {

        if (wlascicielUpdateModelSamochodu.isDisable() == true) {
            wlascicielUpdateModelSamochodu.setDisable(false);
        } else {
            wlascicielUpdateModelSamochodu.setDisable(true);
        }

    }

    public void updateWlasciciel(ActionEvent actionEvent) throws SQLException, IOException {


        int idWlasciciela = (int) listWlasciciel.get(selectWlasciciel.getSelectionModel().getSelectedIndex());


        if (wlascicielUpdateImie.isDisable() == false || wlascicielUpdateNazwisko.isDisable() == false || wlascicielUpdateModelSamochodu.isDisable() == false || wlascicielUpdateMarkaSamochodu.isDisable() == false)

            {
                if (wlascicielUpdateImie.isDisable() == false)

                    {


                        if(wlascicielUpdateImie.getText().equals("") || wlascicielUpdateImie.getText().charAt(0) == ' ')
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Błąd!");
                            alert.setHeaderText("Nie wpisano poprawnie imienia właściciela!");
                            alert.showAndWait();
                        }

                        else {
                            DbConnect dbConnect = new DbConnect();
                            connection = dbConnect.getConnection();


                            Statement zmiana = connection.createStatement();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("UPDATE wlasciciel SET imie_wlasciciela = '");
                            stringBuilder.append(wlascicielUpdateImie.getText());
                            stringBuilder.append("'");
                            stringBuilder.append("WHERE id_wlasciciela=");
                            stringBuilder.append(idWlasciciela);

                            int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                            if(rowsCount>0)
                            {
                                wlascicielUpdateImie.clear();
                                czyWykonane = 1;
                            }
                        }
                }

                if (wlascicielUpdateNazwisko.isDisable() == false) {

                    if(wlascicielUpdateNazwisko.getText().equals("") || wlascicielUpdateNazwisko.getText().charAt(0) == ' ')
                    {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd!");
                        alert.setHeaderText("Nie wpisano poprawnie nazwiska właściciela!");
                        alert.showAndWait();
                    }

                        else
                        {
                    DbConnect dbConnect = new DbConnect();
                    connection = dbConnect.getConnection();


                    Statement zmiana = connection.createStatement();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UPDATE wlasciciel SET nazwisko_wlasciciela = '");
                    stringBuilder.append(wlascicielUpdateNazwisko.getText());
                    stringBuilder.append("'");
                    stringBuilder.append("WHERE id_wlasciciela=");
                    stringBuilder.append(idWlasciciela);

                    int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                    if(rowsCount>0)
                    {
                        wlascicielUpdateNazwisko.clear();
                        czyWykonane = 1;
                    }
                    }
                }


                if (wlascicielUpdateMarkaSamochodu.isDisable() == false) {

                    if(wlascicielUpdateMarkaSamochodu.getText().equals("") || wlascicielUpdateMarkaSamochodu.getText().charAt(0) == ' ')
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd!");
                        alert.setHeaderText("Nie wpisano poprawnie marki samochodu!");
                        alert.showAndWait();
                    }
                    else {
                        DbConnect dbConnect = new DbConnect();
                        connection = dbConnect.getConnection();


                        Statement zmiana = connection.createStatement();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("UPDATE wlasciciel SET marka_samochodu_wlasciciela = '");
                        stringBuilder.append(wlascicielUpdateMarkaSamochodu.getText());
                        stringBuilder.append("'");
                        stringBuilder.append("WHERE id_wlasciciela=");
                        stringBuilder.append(idWlasciciela);

                        int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                        if(rowsCount>0)
                        {
                            wlascicielUpdateMarkaSamochodu.clear();
                            czyWykonane = 1;
                        }
                    }
                }

                if (wlascicielUpdateModelSamochodu.isDisable() == false) {
                    if(wlascicielUpdateModelSamochodu.getText().equals("") || wlascicielUpdateModelSamochodu.getText().charAt(0) == ' ')
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd!");
                        alert.setHeaderText("Nie wpisano poprawnie modelu samochodu!");
                        alert.showAndWait();


                    }
                    else {
                        DbConnect dbConnect = new DbConnect();
                        connection = dbConnect.getConnection();


                        Statement zmiana = connection.createStatement();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("UPDATE wlasciciel SET model_samochodu_wlasciciela = '");
                        stringBuilder.append(wlascicielUpdateModelSamochodu.getText());
                        stringBuilder.append("'");
                        stringBuilder.append("WHERE id_wlasciciela=");
                        stringBuilder.append(idWlasciciela);

                        int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                        if(rowsCount>0)
                        {
                            wlascicielUpdateModelSamochodu.clear();
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


                    Stage thisStage = (Stage) wlascicielUpdateMarkaSamochodu.getScene().getWindow();
                    Scene thisScene = wlascicielUpdateMarkaSamochodu.getScene();

                    SceneController sceneController = new SceneController(thisStage,thisScene);
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