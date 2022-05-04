package Controller;

import Helper.*;
import Model.*;
import javafx.collections.ObservableList;
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

public class ModifyAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField ModApptApptIDTxtField;

    @FXML
    private Button ModApptCancBtn;

    @FXML
    private TextField ModApptLocationTxtField;

    @FXML
    private TextField ModApptCustomerIDTxtField;

    @FXML
    private TextField ModApptDescriptionTxtField;

    @FXML
    private TextField ModApptEndDateTimeTxtField;

    @FXML
    private ComboBox<Contact> ModApptContactComboBox;

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
        try {
            Integer appointmentID = Integer.parseInt(ModApptApptIDTxtField.getText());
            String title = String.valueOf(ModApptTitleTxtField.getText());
            String description = String.valueOf(ModApptDescriptionTxtField.getText());
            String location = String.valueOf(ModApptLocationTxtField.getText());
            String type = String.valueOf(ModApptTypeTxtField.getText());
            Timestamp startDateAndTime = Timestamp.valueOf(ModApptStartDateTimeTxtField.getText());
            Timestamp endDateAndTime = Timestamp.valueOf(ModApptEndDateTimeTxtField.getText());
            int customerID = Integer.parseInt(ModApptCustomerIDTxtField.getText());
            int userID = Integer.parseInt(ModApptUserIDTxtField.getText());
            int contactID = ModApptContactComboBox.getSelectionModel().getSelectedItem().getID();
            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                dialogBox("Please complete the form and give input for all data.", "Error Message", "Input Error!");
            } else if (CustomersQuery.selectExists(customerID) && UsersQuery.selectExists(userID)) {
                AppointmentsQuery.update(appointmentID, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
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

    public void sendAppointment(Appointment appointment) throws IOException, SQLException {
        ModApptApptIDTxtField.setText(String.valueOf(appointment.getAppointmentID()));
        ModApptTitleTxtField.setText(String.valueOf(appointment.getTitle()));
        ModApptDescriptionTxtField.setText(String.valueOf(appointment.getDescription()));
        ModApptLocationTxtField.setText(String.valueOf(appointment.getLocation()));
        ModApptTypeTxtField.setText(String.valueOf(appointment.getType()));
        ObservableList<Contact> selectedContact = ContactsQuery.lookupContact(appointment.getContactID());
        ModApptContactComboBox.setValue(selectedContact.get(0));
        ModApptStartDateTimeTxtField.setText(String.valueOf(Timestamp.valueOf(String.valueOf(appointment.getStartDateAndTime()))));
        ModApptEndDateTimeTxtField.setText(String.valueOf(Timestamp.valueOf(String.valueOf(appointment.getEndDateAndTime()))));
        ModApptCustomerIDTxtField.setText(String.valueOf(appointment.getCustomerID()));
        ModApptUserIDTxtField.setText(String.valueOf(appointment.getUserID()));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ModApptContactComboBox.setItems(ContactsQuery.populateContactTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
