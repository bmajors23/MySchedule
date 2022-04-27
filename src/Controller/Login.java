package Controller;

import Helper.JDBC;
import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Initializable {

    Stage stage;
    Parent scene;

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
    void OnActionLogin(ActionEvent event) throws IOException, SQLException {

        String sql = "SELECT * FROM client_schedule.users where User_Name like '" + LoginUsernameTextField.getText() + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("Password").equals(LoginPasswordTextField.getText())) {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } else {
                    dialogBox("InvalidPasswordMessage","InvalidPasswordTitle", "InvalidPasswordHeader");
                }
            } else {
                dialogBox("InvalidUsernameMessage","InvalidUsernameTitle", "InvalidUsernameHeader");
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

    }
}
