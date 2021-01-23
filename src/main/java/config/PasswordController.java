package config;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

public class PasswordController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtEmail;


    private Connection connection;
    private DbConnect dbConnect;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public void back(ActionEvent actionEvent)
    {
        try {

            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }


    public void resetPasswordAction(ActionEvent actionEvent) {

        if (resetPassword().equals("Success")) {
            try {

                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbConnect = new DbConnect();

    }

    public PasswordController() {

    }

    private String resetPassword() {
        connection = dbConnect.getConnection();

        String status = "Success";
        String email = txtEmail.getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (email.isEmpty()) {
            setLblError(Color.TOMATO, "Okienko emailu jest puste!");
            status = "Error";
        } else if (matcher.find() == false) {
            setLblError(Color.TOMATO, "Wprowadzony e-mail jest niepoprawny!");
            status = "Error";
        }
         else {

            try {
                {
                    StringBuilder stringBuilderEmail = new StringBuilder();


                    stringBuilderEmail.append("SELECT * FROM uzytkownik WHERE `email_uzytkownika`='");
                    stringBuilderEmail.append(txtEmail.getText());
                    stringBuilderEmail.append("';");


                    Statement sEmail = connection.createStatement();
                    Statement s = connection.createStatement();

                    sEmail.execute(stringBuilderEmail.toString());


                    ResultSet rs = s.getResultSet();
                    ResultSet rsEmail = sEmail.getResultSet();

                    while (!(rsEmail.next())) {
                        setLblError(Color.TOMATO, "Użytkownik o podanym emailu nie istnieje!");
                        status = "Error";
                        return status;
                    }

                }

                setLblError(Color.GREEN, "Reset password Successful..Redirecting..");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }

        }
        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

}
