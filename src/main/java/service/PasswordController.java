package service;

import connect.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

public class PasswordController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField txtEmail;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int TCP_SERVER_PORT = 3306;
    private static boolean connected = false;
    static Socket s;


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


    public void resetPasswordAction(ActionEvent actionEvent) {

        if (resetPassword().equals("Success")) {
            try {

                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/login.fxml")));

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbConnect = new DbConnect();

        Timer timer = new Timer();

        timer.schedule(task,01,1000);

    }

    public PasswordController() {

    }

    @FXML
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
