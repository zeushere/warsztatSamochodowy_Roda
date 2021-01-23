package config;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;;

public class LoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    private Connection connection;
    private DbConnect dbConnect;




    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/main.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }


    public void btnSignupAction(ActionEvent actionEvent) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/register.fxml")));
                stage.setScene(scene);
                stage.show();


            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbConnect = new DbConnect();


            if(dbConnect.getConnection() != null) {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Serwer odpowiada, możesz się zalogować!");
            }
            else
            {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Brak połączenia z serwerem! Spróbuj ponownie później.");
                txtPassword.setDisable(true);
                txtUsername.setDisable(true);
                btnSignin.setDisable(true);
                btnSignup.setDisable(true);


        }
    }


    public LoginController() {

    }

    private String logIn() {
        connection=dbConnect.getConnection();

        String status = "Success";
        String login = txtUsername.getText();
        String password = txtPassword.getText();
        if(login.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {

            String sql = "SELECT * FROM uzytkownik Where nazwa_uzytkownika = ? and haslo_uzytkownika = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet resultSet;
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Login/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
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
