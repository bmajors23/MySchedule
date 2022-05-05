package Controller;

import Helper.AppointmentsQuery;
import Helper.CustomersQuery;
import Model.Appointment;
import Model.SavedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentMenu implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button ApptMenuAddApptBtn;

    @FXML
    private Button ApptMenuDelApptBtn;

    @FXML
    private Button ApptMenuMmBtn;

    @FXML
    private RadioButton ApptMenuMonthRdBtn;

    @FXML
    private TableColumn<Appointment, String> ApptMenuTblLocationCol;

    @FXML
    private TableView<Appointment> ApptMenuTbl;

    @FXML
    private TableColumn<Appointment, Integer> ApptMenuTblApptIDCol;

    @FXML
    private TableColumn<Appointment, Integer> ApptMenuTblContactCol;

    @FXML
    private TableColumn<Appointment, Integer> ApptMenuTblCustomerIDCol;

    @FXML
    private TableColumn<Appointment, String> ApptMenuTblDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> ApptMenuTblEndDateTimeCol;

    @FXML
    private TableColumn<Appointment, Timestamp> ApptMenuTblStartDateTimeCol;

    @FXML
    private DatePicker ApptMenuDatePicker;

    @FXML
    private TableColumn<Appointment, String> ApptMenuTblTitleCol;

    @FXML
    private TableColumn<Appointment, String> ApptMenuTblTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> ApptMenuTblUserIDCol;

    @FXML
    private RadioButton ApptMenuViewAllRdBtn;

    @FXML
    private RadioButton ApptMenuWeekRdBtn;

    @FXML
    private ToggleGroup MonthWeekView;

    @FXML
    void OnActionDeleteAppt(ActionEvent event) throws SQLException {
        if (ApptMenuTbl.getSelectionModel().isEmpty()) {
            dialogBox("Error. No appointment is selected. Please select a appointment to delete.", "Error Message", "Deletion Error!");
        } else {
                String titleBar = "Confirmation Message";
                String headerMessage = "Confirmation Message";
                String infoMessage = "Are you sure you want to delete?";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(infoMessage);
                alert.setTitle(titleBar);
                alert.setHeaderText(headerMessage);
                Optional<ButtonType> choice = alert.showAndWait();

                if (choice.get() == ButtonType.OK) {
                    AppointmentsQuery.delete(ApptMenuTbl.getSelectionModel().getSelectedItem().getAppointmentID());
                    ApptMenuTbl.setItems(AppointmentsQuery.populateAppointmentTable());
                } else {

                }
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

    @FXML
    void OnActionDisplayAddAppt(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionDisplayModAppt(ActionEvent event) throws IOException, SQLException {
        if (ApptMenuTbl.getSelectionModel().isEmpty()) {
            dialogBox("Error. No appointment is selected. Please select an appointment to modify.", "Error Message", "Modify Error!");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyAppointment.fxml"));
            loader.load();
            ModifyAppointment MAController = loader.getController();
            MAController.sendAppointment(ApptMenuTbl.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void OnActionDisplayMonthTblView(ActionEvent event) throws SQLException {
        ApptMenuTbl.setItems(AppointmentsQuery.filterByMonth(ApptMenuDatePicker.getValue()));
    }

    @FXML
    void OnActionDisplayWeekTblView(ActionEvent event) throws SQLException {
        ApptMenuTbl.setItems(AppointmentsQuery.filterByWeek(ApptMenuDatePicker.getValue()));
    }

    @FXML
    void OnActionPopulateAllAppointments(ActionEvent event) throws SQLException {
        ApptMenuTbl.setItems(AppointmentsQuery.populateAppointmentTable());
    }

    @FXML
    void OnActionSelectDate(ActionEvent event) throws SQLException {
        if (MonthWeekView.getSelectedToggle() == ApptMenuViewAllRdBtn) {
            ApptMenuTbl.setItems(AppointmentsQuery.populateAppointmentTable());
        } else if (MonthWeekView.getSelectedToggle() == ApptMenuMonthRdBtn) {
            ApptMenuTbl.setItems(AppointmentsQuery.filterByMonth(ApptMenuDatePicker.getValue()));
        } else if (MonthWeekView.getSelectedToggle() == ApptMenuWeekRdBtn) {
            ApptMenuTbl.setItems(AppointmentsQuery.filterByWeek(ApptMenuDatePicker.getValue()));
        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ApptMenuTbl.setItems(AppointmentsQuery.populateAppointmentTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ApptMenuDatePicker.setValue(ZonedDateTime.now().toLocalDate());

        ApptMenuTblApptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        ApptMenuTblTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ApptMenuTblDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ApptMenuTblLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        ApptMenuTblTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ApptMenuTblStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        ApptMenuTblEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        ApptMenuTblCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ApptMenuTblUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        ApptMenuTblContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }
}
