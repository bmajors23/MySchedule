package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField ModApptApptIDTxtField;

    @FXML
    private Button ModApptCancBtn;

    @FXML
    private TextField ModApptContactTxtField;

    @FXML
    private TextField ModApptCustomerIDTxtField;

    @FXML
    private TextField ModApptDescriptionTxtField;

    @FXML
    private TextField ModApptEndDateTimeTxtField;

    @FXML
    private ComboBox<?> ModApptLocationComboBox;

    @FXML
    private Button ModApptSaveBtn;

    @FXML
    private TextField ModApptStartDateTimeTxtField;

    @FXML
    private TextField ModApptTitleTxtField;

    @FXML
    private TextField ModApptTypeTxtField;

    @FXML
    private TextField ModApptUserIDTxtField;

    @FXML
    void OnActionDisplayApptMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionSaveAppt(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
