package Controller;

import Helper.Helper;
import Helper.JDBC;
import Helper.AppointmentsQuery;
import Helper.UsersQuery;
import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Login implements Initializable {

    Stage stage;
    Parent scene;
    LocalDateTime loginTime;
    String password;
    String filename = "C:/Users/LabUser/IdeaProjects/Software2New/login_activity.txt";
    FileWriter outputFile = new FileWriter(filename, true);



    @FXML
    private Label LoginCountryLabel;

    @FXML
    private Button LoginLoginButton;

    @FXML
    private TextField LoginPasswordTextField;

    @FXML
    private TextField LoginUsernameTextField;

    @FXML
    private Label LoginPasswordLabel;

    @FXML
    private Label LoginLoginLabel;

    @FXML
    private Label LoginUsernameLabel;

    @FXML
    private CheckBox LoginShowPassword;

    public Login() throws IOException {
    }

    @FXML
    void OnActionLogin(ActionEvent event) throws IOException, SQLException {

        String sql = "SELECT * FROM client_schedule.users where User_Name like '" + LoginUsernameTextField.getText() + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("Password").equals(LoginPasswordTextField.getText())) {
                    LocalDateTime loginTime = LocalDateTime.now();
                    Timestamp loginTimestamp = Timestamp.valueOf(loginTime);
                    AppointmentsQuery.appointmentSoon(loginTimestamp);
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                    outputFile.append("User ").append(rs.getString("User_Name")).append(" successfully logged in at ").append(String.valueOf(LocalDateTime.now())).append("\n");
                    outputFile.close();


                } else {
                    dialogBox("InvalidPasswordMessage","InvalidPasswordTitle", "InvalidPasswordHeader");
                    outputFile.append("User ").append(rs.getString("User_Name")).append(" gave invalid login at ").append(String.valueOf(LocalDateTime.now())).append("\n");
                    outputFile.close();
                }
            } else {
                dialogBox("InvalidUsernameMessage","InvalidUsernameTitle", "InvalidUsernameHeader");
                outputFile.append("User ").append(LoginUsernameTextField.getText()).append(" gave invalid login at ").append(String.valueOf(LocalDateTime.now())).append("\n");
                outputFile.close();
            }
    }
        public void dialogBox(String infoMessage, String titleBar, String headerMessage) {
            ResourceBundle rb = ResourceBundle.getBundle("Helper/Nat", Locale.getDefault());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rb.getString(infoMessage));
            alert.setTitle(rb.getString(titleBar));
            alert.setHeaderText(rb.getString(headerMessage));
            alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResourceBundle rb = ResourceBundle.getBundle("Helper/Nat", Locale.getDefault());
        String zoneID = Locale.getDefault().getCountry();

            LoginLoginButton.setText(rb.getString("Login"));
            LoginUsernameLabel.setText(rb.getString("Username"));
            LoginPasswordLabel.setText(rb.getString("Password"));
            LoginLoginLabel.setText(rb.getString("Login"));
            LoginCountryLabel.setText("ZoneID: "+ zoneID);

            LoginShowPassword.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                if (LoginPasswordTextField.getText().isEmpty()) {
                    password = LoginPasswordTextField.getPromptText();
                    LoginPasswordTextField.clear();
                    LoginPasswordTextField.setText(password);
                } else {
                    password = LoginPasswordTextField.getText();
                    LoginPasswordTextField.clear();
                    LoginPasswordTextField.setPromptText(password);
                }
            });

    }
}
