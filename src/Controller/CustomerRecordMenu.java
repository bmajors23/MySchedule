package Controller;

import Helper.AppointmentsQuery;
import Helper.CustomersQuery;
import Model.Customer;
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
import java.util.Optional;
import java.util.ResourceBundle;

/** CustomerRecord Menu. This class allows the user to navigate the customer menu and make changes to customers such
 * as add, modify, and delete.
 *
 */
public class CustomerRecordMenu implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button CustomerRecordMenuAddCustomerBtn;

    @FXML
    private Button CustomerRecordMenuDelCustomerBtn;

    @FXML
    private Button CustomerRecordMenuMmBtn;

    @FXML
    private Button CustomerRecordMenuModCustomerBtn;

    @FXML
    private TableView<Customer> CustomerRecordMenuTbl;

    @FXML
    private TableColumn<Customer, String> CustomerRecordMenuTblAddressCol;

    @FXML
    private TableColumn<Customer, Integer> CustomerRecordMenuTblCustomerIDCol;

    @FXML
    private TableColumn<Customer, String> CustomerRecordMenuTblCustomerNameCol;

    @FXML
    private TableColumn<Customer, String> CustomerRecordsMenuTblPostalCodeCol;

    @FXML
    private TableColumn<Customer, String> CustomerRecordsMenuTblPhoneCol;

    @FXML
    private TableColumn<Customer, Integer> CustomerRecordMenuTblStateCol;

    /** This method will allow the user to delete a customer from the sql database
     * @param event
     * @throws SQLException
     */
    @FXML
    void OnActionDeleteCustomer(ActionEvent event) throws SQLException {
        if (CustomerRecordMenuTbl.getSelectionModel().isEmpty()) {
            dialogBox("Error. No customer is selected. Please select a customer to delete.", "Error Message", "Deletion Error!");
        } else {
            boolean associatedAppointment = AppointmentsQuery.associatedAppointment(CustomerRecordMenuTbl.getSelectionModel().getSelectedItem().getCustomerID());
            if (associatedAppointment) {
                String titleBar = "Confirmation Message";
                String headerMessage = "Confirmation Message";
                String infoMessage = "Are you sure you want to delete?";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(infoMessage);
                alert.setTitle(titleBar);
                alert.setHeaderText(headerMessage);
                Optional<ButtonType> choice = alert.showAndWait();

                if (choice.get() == ButtonType.OK) {
                    CustomersQuery.delete(CustomerRecordMenuTbl.getSelectionModel().getSelectedItem().getCustomerID());
                    CustomerRecordMenuTbl.setItems(CustomersQuery.populateCustomerTable());
                } else {

                }
            } else {
                dialogBox("Error. This customer has an appointment. Can't delete.", "Error Message", "Deletion Error!");
            }
        }

    }

    /** This method will navigate the user to the add customer form and allow the user to add to the sql database
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
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

    /** This method will navigate the user to the mod customer form where they can modify existing customer data
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void OnActionDisplayModCustomer(ActionEvent event) throws IOException, SQLException {
        if (CustomerRecordMenuTbl.getSelectionModel().isEmpty()) {
            dialogBox("Error. No customer is selected. Please select a customer to modify.", "Error Message", "Modify Error!");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyCustomer.fxml"));
            loader.load();
            ModifyCustomer MCController = loader.getController();
            MCController.sendCustomer(CustomerRecordMenuTbl.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /** This method will be called whenever we want to create a dialog box to convey information to the user
     * @param infoMessage
     * @param titleBar
     * @param headerMessage
     */
    public static void dialogBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    /** Initialize method, this method populates the customer record table with data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            CustomerRecordMenuTbl.setItems(CustomersQuery.populateCustomerTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CustomerRecordMenuTblCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        CustomerRecordMenuTblCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerRecordMenuTblAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        CustomerRecordsMenuTblPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerZip"));
        CustomerRecordsMenuTblPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        CustomerRecordMenuTblStateCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

    }
}
