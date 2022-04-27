package Controller;

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
    private TableColumn<?, ?> ApptMenuTblLocationCol;

    @FXML
    private TableView<Appointment> ApptMenuTbl;

    @FXML
    private TableColumn<?, ?> ApptMenuTblApptIDCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblContactCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblCustomerIDCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblDescriptionCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblEndDateTimeCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblStartDateTimeCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblTitleCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblTypeCol;

    @FXML
    private TableColumn<?, ?> ApptMenuTblUserIDCol;

    @FXML
    private RadioButton ApptMenuWeekRdBtn;

    @FXML
    private ToggleGroup MonthWeekView;

    @FXML
    void OnActionDeleteAppt(ActionEvent event) {

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
    void OnActionDisplayModAppt(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionDisplayMonthTblView(ActionEvent event) {

    }

    @FXML
    void OnActionDisplayWeekTblView(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ApptMenuTbl.setItems(SavedData.getAllAppointments());

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
