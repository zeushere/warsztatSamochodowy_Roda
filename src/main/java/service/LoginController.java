package service;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
import javafx.scene.layout.AnchorPane;
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
    private  Label btnForgot;

    @FXML
    private Button btnSignup;

    @FXML
    private AnchorPane anchorPane;

    private Connection connection;
    private DbConnect dbConnect;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int TCP_SERVER_PORT = 3306;
    private static boolean connected = false;
    static Socket s;

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/main.fxml")));

                    String pathName = "src/main/java/stylesheets/styles.css";
                    File file = new File(pathName);
                    if (file.exists()) {
                        scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
                    } else {
                        System.out.println("Could not find css file: "+pathName);
                    }

                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

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

    public void btnSignupAction(ActionEvent actionEvent) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/register.fxml")));

                String pathName = "src/main/java/stylesheets/styles.css";
                File file = new File(pathName);
                if (file.exists()) {
                    scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
                } else {
                    System.out.println("Could not find css file: "+pathName);
                }

                stage.setScene(scene);
                stage.show();


            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }



    @Override
    public void initialize(URL url, ResourceBundle rb) {


        Timer timer = new Timer();
        timer.schedule(task, 01, 1000);

    }


    public LoginController() {

    }

    private String logIn() {
        dbConnect = new DbConnect();

        String status = "Success";
        String login = txtUsername.getText();
        String password = txtPassword.getText();
        if(login.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Puste znaki!");
            status = "Error";
        } else {

            String sql = "SELECT * FROM uzytkownik Where nazwa_uzytkownika = ? and haslo_uzytkownika = ?";
            try {
                PreparedStatement preparedStatement = dbConnect.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet resultSet;
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Wprowadź prawidłową nazwę użytkownika/hasło!");
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


    public void btnForgotAction(MouseEvent mouseEvent) {

        try {
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/password.fxml")));

            String pathName = "src/main/java/stylesheets/styles.css";
            File file = new File(pathName);
            if (file.exists()) {
                scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
            } else {
                System.out.println("Could not find css file: "+pathName);
            }

            stage.setScene(scene);
            stage.show();


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

}