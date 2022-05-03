package Controller;

import Helper.AppointmentsQuery;
import Helper.CustomersQuery;
import Model.Appointment;
import Model.Customer;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField AddApptApptIDTxtField;

    @FXML
    private Button AddApptCancBtn;

    @FXML
    private TextField AddApptContactTxtField;

    @FXML
    private TextField AddApptCustomerIDTxtField;

    @FXML
    private TextField AddApptDescriptionTxtField;

    @FXML
    private TextField AddApptEndDateTimeTxtField;

    @FXML
    private ComboBox<?> AddApptLocationComboBox;

    @FXML
    private Button AddApptSaveBtn;

    @FXML
    private TextField AddApptStartDateTimeTxtField;

    @FXML
    private TextField AddApptTitleTxtField;

    @FXML
    private TextField AddApptTypeTxtField;

    @FXML
    private TextField AddApptUserIDTxtField;

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
        int maxId = 1;
        try {
            for (Appointment appointment : AppointmentsQuery.populateApptTable()) {
                if (appointment.getAppointmentID() > maxId) {
                    maxId = appointment.getAppointmentID();
                } else {
                    continue;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AddApptApptIDTxtField.setText(String.valueOf(maxId + 1));
    }
}
