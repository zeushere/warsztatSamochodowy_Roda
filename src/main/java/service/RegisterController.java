package service;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;;

public class RegisterController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnSignup;


    private Connection connection;
    private DbConnect dbConnect;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int TCP_SERVER_PORT = 3306;
    private static boolean connected = false;
    static Socket s;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static  final Pattern VALID_LOGIN_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{4,}$", Pattern.CASE_INSENSITIVE);

    public static  final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$", Pattern.CASE_INSENSITIVE);

    public void btnSignupAction(ActionEvent actionEvent) {

        if (registerIn().equals("Success")) {
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

    TimerTask task = new TimerTask() {


        @Override
        public void run() {
            if (connected == false) {

                if(hostAvailabilityCheck() == true) {
                    try {

                    }
                    catch (Exception ex)
                    {

                    }

                    finally {
                        anchorPane.setDisable(false);
                    }
                }
                else if(hostAvailabilityCheck() == false)
                {

                    try
                    {

                    }
                    catch (Exception ex)
                    {

                    }

                    finally {
                        anchorPane.setDisable(true);
                    }

                }
            }
        }
    };

    public boolean hostAvailabilityCheck()
    {

        boolean available = true;
        try {
            if (connected == false)
            {
                (s = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT)).close();
            }


        }
        catch (Exception e)
        {
            available = false;
            s = null;
        }
        return available;
    }

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbConnect = new DbConnect();

        Timer timer = new Timer();

        timer.schedule(task,01,1000);

    }

    public RegisterController() {

    }

    private String registerIn() {
        connection = dbConnect.getConnection();

        String status = "Success";
        String login = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        Matcher matcherLogin = VALID_LOGIN_REGEX.matcher(login);
        Matcher matcherPassword = VALID_PASSWORD_REGEX.matcher(password);

        if (email.isEmpty() || password.isEmpty() || login.isEmpty()) {
            setLblError(Color.TOMATO, "Puste znaki!");
            status = "Error";
        } else if (matcher.find() == false) {
            setLblError(Color.TOMATO, "Wprowadzony e-mail jest niepoprawny!");
            status = "Error";
        } else if(matcherLogin.find() == false) {
            setLblError(Color.TOMATO, "Wprowadzony login jest niepoprawny!");
            status = "Error";
        }
        else if(matcherPassword.find() == false)
        {
            setLblError(Color.TOMATO, "Wprowadzone hasło jest niepoprawne!");
            status = "Error";
        }

         else {

            try {
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    StringBuilder stringBuilderEmail = new StringBuilder();
                    stringBuilder.append("SELECT * FROM uzytkownik WHERE `nazwa_uzytkownika`='");
                    stringBuilder.append(txtUsername.getText());
                    stringBuilder.append("';");

                    stringBuilderEmail.append("SELECT * FROM uzytkownik WHERE `email_uzytkownika`='");
                    stringBuilderEmail.append(txtEmail.getText());
                    stringBuilderEmail.append("';");


                    Statement sEmail = connection.createStatement();
                    Statement s = connection.createStatement();

                    s.execute(stringBuilder.toString());
                    sEmail.execute(stringBuilderEmail.toString());


                    ResultSet rs = s.getResultSet();
                    ResultSet rsEmail = sEmail.getResultSet();

                    while (rs.next() || rsEmail.next()) {
                        setLblError(Color.TOMATO, "Użytkownik o podanej nazwie uzytkownika lub emailu juz istnieje!");
                        status = "Error";
                        return status;
                    }


                    String query = "INSERT INTO uzytkownik(nazwa_uzytkownika, haslo_uzytkownika, email_uzytkownika) VALUES('"
                            + txtUsername.getText() + "','"
                            + txtPassword.getText() + "','" + txtEmail.getText() + "')";
                    int execute = connection.createStatement().executeUpdate(query);
                    if (execute > 0) {
                        txtUsername.clear();
                        txtPassword.clear();
                        txtEmail.clear();
                    }
                }

                setLblError(Color.GREEN, "Register Successful..Redirecting..");
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
