package Controller;

import Helper.AppointmentsQuery;
import Model.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Reports class, this class allows the user to navigate the reports form and generate reports from the sql database
 *
 */
public class Reports implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button ReportOneButton;

    @FXML
    private TextField ReportOneMonthTxtField;

    @FXML
    private TextField ReportOneTypeTxtField;

    @FXML
    private Button ReportThreeButton;

    @FXML
    private TextField ReportThreeCustomerTxtField;

    @FXML
    private Button ReportTwoButton;

    @FXML
    private TextField ReportTwoContactTxtField;

    /** This method will generate the first report
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void OnActionGenerateReportOne(ActionEvent event) throws SQLException, IOException {
        ObservableList<Appointment> filteredAppointments = AppointmentsQuery.reportOne(ReportOneTypeTxtField.getText(), ReportOneMonthTxtField.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ReportsResults.fxml"));
        loader.load();
        ReportsResults RRController = loader.getController();
        RRController.populateTbl(filteredAppointments);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will generate the second report
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void OnActionGenerateReportTwo(ActionEvent event) throws IOException, SQLException {
        ObservableList<Appointment> filteredAppointments = AppointmentsQuery.reportTwo(ReportTwoContactTxtField.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ReportsResults.fxml"));
        loader.load();
        ReportsResults RRController = loader.getController();
        RRController.populateTbl(filteredAppointments);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will generate the third report
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void OnActionGenerateReportThree(ActionEvent event) throws SQLException, IOException {
        ObservableList<Appointment> filteredAppointments = AppointmentsQuery.reportThree(ReportThreeCustomerTxtField.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ReportsResults.fxml"));
        loader.load();
        ReportsResults RRController = loader.getController();
        RRController.populateTbl(filteredAppointments);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will return the user to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Initialize method
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
