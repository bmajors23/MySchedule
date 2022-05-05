package Controller;

import Model.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** ReportsResults class. This class will allow the user to view the generated reports from the reports form
 *
 */
public class ReportsResults implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button ReportResultsBtn;

    @FXML
    private TableView<Appointment> ReportResultsTbl;

    @FXML
    private TableColumn<Appointment, Integer> ReportResultsTblApptIDCol;

    @FXML
    private TableColumn<Appointment, ComboBox> ReportResultsTblContactCol;

    @FXML
    private TableColumn<Appointment, Integer> ReportResultsTblCustomerIDCol;

    @FXML
    private TableColumn<Appointment, String> ReportResultsTblDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> ReportResultsTblEndDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> ReportResultsTblLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> ReportResultsTblStartDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> ReportResultsTblTitleCol;

    @FXML
    private TableColumn<Appointment, String> ReportResultsTblTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> ReportResultsTblUserIDCol;

    /** This method will retrun the user to the reports menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionReturnToReportsMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will populate the table view
     * @param data
     */
    public void populateTbl(ObservableList<Appointment> data) {
        ReportResultsTbl.setItems(data);
    }

    /** Initialize method. This method will populate the table view
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ReportResultsTblApptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        ReportResultsTblTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ReportResultsTblDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ReportResultsTblLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        ReportResultsTblTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ReportResultsTblStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateAndTime"));
        ReportResultsTblEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateAndTime"));
        ReportResultsTblCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ReportResultsTblUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        ReportResultsTblContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }
}
