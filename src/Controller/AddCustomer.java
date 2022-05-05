package Controller;

import Helper.CountriesQuery;
import Helper.CustomersQuery;
import Helper.DivisionsQuery;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
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
import java.util.ResourceBundle;

/** This is the add customer class that will allow the user to add customers to the sql database
 *
 */
public class AddCustomer implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField AddCustomerAddressTxtField;

    @FXML
    private Button AddCustomerCancBtn;

    @FXML
    private ComboBox<Country> AddCustomerCountryComboBox;

    @FXML
    private TextField AddCustomerCustomerIDTxtField;

    @FXML
    private TextField AddCustomerCustomerNameTxtField;

    @FXML
    private TextField AddCustomerPhoneNumberTxtField;

    @FXML
    private TextField AddCustomerPostalCodeTxtField;

    @FXML
    private Button AddCustomerSaveBtn;

    @FXML
    private ComboBox<FirstLevelDivision> AddCustomerStateProvinceComboBox;

    /** This method will return the user to the Customer Record Menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionDisplayCustomerMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method will save the customer data and return the user to the customer record menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionSaveCustomer(ActionEvent event) throws IOException {

        try {
            String name = String.valueOf(AddCustomerCustomerNameTxtField.getText());
            String address = String.valueOf(AddCustomerAddressTxtField.getText());
            String postal = String.valueOf(AddCustomerPostalCodeTxtField.getText());
            String phone = String.valueOf(AddCustomerPhoneNumberTxtField.getText());
            int division = AddCustomerStateProvinceComboBox.getSelectionModel().getSelectedItem().getDivisionID();

            if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty() || !(division > 0 && division < 105)) {
                dialogBox("Please complete the form and give input for all data.", "Error Message", "Input Error!");
            } else {
                CustomersQuery.insert(name, address, postal, phone, division);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            dialogBox("Please complete the form and give input for all data.", "Error Message", "Input Error!");
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

    /** This method will update the division combo box based on what the user entered into the country combo box
     * @param event
     */
    @FXML
    void OnActionUpdateCountryComboBox(ActionEvent event) {
        if (AddCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("U.S")) {
            try {
                AddCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        } else if (AddCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("UK")) {
            try {
                AddCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(2));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (AddCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("Canada")) {
            try {
                AddCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(3));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Intialize method. This will autogenerate customer ID's and will populate the combo boxes with data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int maxId = 1;
        try {
            for (Customer customer : CustomersQuery.populateCustomerTable()) {
                if (customer.getCustomerID() > maxId) {
                    maxId = customer.getCustomerID();
                } else {
                    continue;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AddCustomerCustomerIDTxtField.setText(String.valueOf(maxId + 1));

        try {
            AddCustomerCountryComboBox.setItems(CountriesQuery.populateCountryTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            AddCustomerStateProvinceComboBox.setItems(DivisionsQuery.populateDivisionTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
