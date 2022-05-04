package Controller;

import Helper.*;
import Model.Appointment;
import Model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField AddApptApptIDTxtField;

    @FXML
    private Button AddApptCancBtn;

    @FXML
    private TextField AddApptLocationTxtField;

    @FXML
    private TextField AddApptCustomerIDTxtField;

    @FXML
    private TextField AddApptDescriptionTxtField;

    @FXML
    private TextField AddApptEndDateTimeTxtField;

    @FXML
    private ComboBox<Contact> AddApptContactComboBox;

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
    void OnActionUpdateContactComboBox(ActionEvent event) {

    }

    @FXML
    void OnActionDisplayApptMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionSaveAppt(ActionEvent event) throws IOException {

        try {
            String title = String.valueOf(AddApptTitleTxtField.getText());
            String description = String.valueOf(AddApptDescriptionTxtField.getText());
            String location = String.valueOf(AddApptLocationTxtField.getText());
            String type = String.valueOf(AddApptTypeTxtField.getText());
            Timestamp startDateAndTime = Timestamp.valueOf(AddApptStartDateTimeTxtField.getText());
            Timestamp endDateAndTime = Timestamp.valueOf(AddApptEndDateTimeTxtField.getText());
            int customerID = Integer.parseInt(AddApptCustomerIDTxtField.getText());
            int userID = Integer.parseInt(AddApptUserIDTxtField.getText());
            int contactID = AddApptContactComboBox.getSelectionModel().getSelectedItem().getID();
            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                dialogBox("Please complete the form and give input for all data.", "Error Message", "Input Error!");
            } else if (CustomersQuery.selectExists(customerID) && UsersQuery.selectExists(userID)) {
                AppointmentsQuery.insert(title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                dialogBox("Please enter an existing Customer ID or User ID.", "Error Message", "Input Error");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            dialogBox("Please enter valid data.", "Error Message", "Input Error!");
        }
    }

    public static void dialogBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int maxId = 1;
        try {
            for (Appointment appointment : AppointmentsQuery.populateAppointmentTable()) {
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

        try {
            AddApptContactComboBox.setItems(ContactsQuery.populateContactTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
