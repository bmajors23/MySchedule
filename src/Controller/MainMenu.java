package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** MainMenu class. This class will serve as the central navigation hub for the user
 *
 */
public class MainMenu implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button MmAppointmentsBtn;

    @FXML
    private Button MmCustomerRecordsBtn;

    @FXML
    private Button MmReportsBtn;

    @FXML
    private Button MmLogOutBtn;

    /** This method will navigate the user to the Appointments menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayAppointmentsMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will navigate the user to the reports menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayReportsMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will navigate the user to the customer records menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayCustomerRecordsMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will log the user out of the application
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionLogOut(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
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
