package config.update;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class updateNaprawa implements Initializable {
    @FXML
    private ComboBox selectNaprawa;

    @FXML
    private DatePicker naprawaUpdateDataNaprawy;

    @FXML
    private Button naprawaUpdateButton;

    @FXML
    private TextField naprawaUpdateKosztNaprawy;

    @FXML
    private CheckBox updateCheckboxKosztNaprawy;

    @FXML
    private CheckBox updateCheckboxDataNaprawy;

    private Connection connection;
    ArrayList listNaprawa = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectNaprawa();
        naprawaUpdateButton.setDisable(true);
        naprawaUpdateDataNaprawy.setDisable(true);
        naprawaUpdateKosztNaprawy.setDisable(true);
    }

    private void selectNaprawa() {
        try {
            DbConnect dbConnect = new DbConnect();
            connection = dbConnect.getConnection();
            String query = "SELECT *,CONCAT('Wlasciciel: ',wlasciciel.imie_wlasciciela,' '," +
                    "wlasciciel.nazwisko_wlasciciela," +
                    "', Usługa: ',usluga.nazwa_uslugi,', ',usluga.rodzaj_uslugi," +
                    "', Koszt naprawy: ',koszt_naprawy,'zł' " +
                    "', Data naprawy: ',data_naprawy)as naprawa " +
                    "FROM naprawa INNER JOIN wlasciciel ON naprawa.id_wlasciciela=wlasciciel.id_wlasciciela" +
                    " INNER JOIN usluga ON naprawa.id_uslugi=usluga.id_uslugi";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                selectNaprawa.getItems().add(rs.getString("naprawa"));
                listNaprawa.add(rs.getInt("id_naprawy"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void selectNaprawaAction(ActionEvent actionEvent) throws SQLException{

        if(selectNaprawa.getSelectionModel().isEmpty() == false)
        {
            naprawaUpdateButton.setDisable(false);
        }
    }


    public void checkBoxKosztNaprawy(ActionEvent actionEvent) throws SQLException{

        if (naprawaUpdateKosztNaprawy.isDisable() == true) {
            naprawaUpdateKosztNaprawy.setDisable(false);
        } else {
            naprawaUpdateKosztNaprawy.setDisable(true);
        }
    }

    public void checkBoxDataNaprawy(ActionEvent actionEvent) throws SQLException{

        if (naprawaUpdateDataNaprawy.isDisable() == true) {
            naprawaUpdateDataNaprawy.setDisable(false);
        } else {
            naprawaUpdateDataNaprawy.setDisable(true);
        }
    }

    public void naprawaUpdate(ActionEvent actionEvent) throws SQLException{

        int idNaprawy = (int) listNaprawa.get(selectNaprawa.getSelectionModel().getSelectedIndex());


        if (naprawaUpdateKosztNaprawy.isDisable() == false || naprawaUpdateDataNaprawy.isDisable() == false)

        {
        if (naprawaUpdateKosztNaprawy.isDisable() == false)

        {

        if(naprawaUpdateKosztNaprawy.getText().equals("") || naprawaUpdateKosztNaprawy.getText().charAt(0) == ' ')
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!");
        alert.setHeaderText("Nie wpisano poprawnie kosztu usługi!");
        alert.showAndWait();
        }

        else {
        DbConnect dbConnect = new DbConnect();
        connection = dbConnect.getConnection();


        Statement zmiana = connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE naprawa SET koszt_naprawy = '");
        stringBuilder.append(naprawaUpdateKosztNaprawy.getText());
        stringBuilder.append("'");
        stringBuilder.append("WHERE id_naprawy=");
        stringBuilder.append(idNaprawy);

        int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

        if(rowsCount>0)
        {
            naprawaUpdateKosztNaprawy.clear();
        }

        }
        }

        if (naprawaUpdateDataNaprawy.isDisable() == false) {

        try{

            LocalDate dataNaprawy = naprawaUpdateDataNaprawy.getValue();

                DbConnect dbConnect = new DbConnect();
                connection = dbConnect.getConnection();


                Statement zmiana = connection.createStatement();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UPDATE naprawa SET data_naprawy = '");
                stringBuilder.append(dataNaprawy);
                stringBuilder.append("'");
                stringBuilder.append("WHERE id_naprawy=");
                stringBuilder.append(idNaprawy);

                int rowsCount = zmiana.executeUpdate(stringBuilder.toString());

                if (rowsCount>0)
                {
                    naprawaUpdateDataNaprawy.getEditor().clear();
                }

        }

        catch(Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Nie wybrano poprawnie daty naprawy!");
            alert.showAndWait();
        }
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

