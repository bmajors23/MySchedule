package Controller;

import Helper.CountriesQuery;
import Helper.CustomersQuery;
import Helper.DivisionsQuery;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
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
import java.sql.Array;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Modify Customer class. This class allows the user to navigate the modify customer form and update existing customer data
 *
 */
public class ModifyCustomer implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField ModCustomerAddressTxtField;

    @FXML
    private Button ModCustomerCancBtn;

    @FXML
    private ComboBox<Country> ModCustomerCountryComboBox;

    @FXML
    private TextField ModCustomerCustomerIDTxtField;

    @FXML
    private TextField ModCustomerCustomerNameTxtField;

    @FXML
    private TextField ModCustomerPhoneNumberTxtField;

    @FXML
    private TextField ModCustomerPostalCodeTxtField;

    @FXML
    private Button ModCustomerSaveBtn;

    @FXML
    private ComboBox<FirstLevelDivision> ModCustomerStateProvinceComboBox;

    /** This method will return the user to the customer record menu
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

    /** This method will update the division combo box based on what was entered into the country combo box
     * @param event
     * @throws SQLException
     */
    @FXML
    void OnActionUpdateCountryComboBox(ActionEvent event) throws SQLException {
        ModCustomerStateProvinceComboBox.setValue(null);
        if (ModCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("U.S")) {
            try {
                ModCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (ModCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("UK")) {
            try {
                ModCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(2));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (ModCustomerCountryComboBox.getSelectionModel().getSelectedItem().toString().equals("Canada")) {
            try {
                ModCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(3));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** This method will save the modified customer data and then return the user to the customer record menu
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionSaveCustomer(ActionEvent event) throws IOException {
        try {
            int customerID = Integer.parseInt(ModCustomerCustomerIDTxtField.getText());
            String name = String.valueOf(ModCustomerCustomerNameTxtField.getText());
            String address = String.valueOf(ModCustomerAddressTxtField.getText());
            String postal = String.valueOf(ModCustomerPostalCodeTxtField.getText());
            String phone = String.valueOf(ModCustomerPhoneNumberTxtField.getText());
            int division = ModCustomerStateProvinceComboBox.getSelectionModel().getSelectedItem().getDivisionID();

            if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty() || !(division > 0 && division < 105)) {
                dialogBox("Please complete the form and give input for all data.", "Error Message", "Input Error!");
            } else {
                CustomersQuery.update(customerID, name, address, postal, phone, division);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            dialogBox("Please complete the form and give input for all data", "Error Message", "Input Error!");
        }
    }

    /** This method allows the program to send data from the tableview in the customer record menu and use that data to
     * populate the fields in the modify customer form
     * @param customer
     * @throws IOException
     * @throws SQLException
     */
    public void sendCustomer(Customer customer) throws IOException, SQLException {
        ModCustomerCustomerIDTxtField.setText(String.valueOf(customer.getCustomerID()));
        ModCustomerCustomerNameTxtField.setText(String.valueOf(customer.getCustomerName()));
        ModCustomerAddressTxtField.setText(String.valueOf(customer.getCustomerAddress()));
        ModCustomerPostalCodeTxtField.setText(String.valueOf(customer.getCustomerZip()));
        ModCustomerPhoneNumberTxtField.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        ObservableList<FirstLevelDivision> selectedDivision = DivisionsQuery.lookupDivision(customer.getDivisionID());
        ModCustomerStateProvinceComboBox.setValue(selectedDivision.get(0));
        ObservableList<Country> selectedCountry = CountriesQuery.populateCountryTable();
        int selectedCountryID = selectedDivision.get(0).getCountryID();
        if (selectedCountryID == 1) {
            ModCustomerCountryComboBox.setValue(selectedCountry.get(0));
        } else if (selectedCountryID == 2) {
            ModCustomerCountryComboBox.setValue(selectedCountry.get(1));
        } else if (selectedCountryID == 3) {
            ModCustomerCountryComboBox.setValue(selectedCountry.get(2));
        }
        ModCustomerStateProvinceComboBox.setItems(DivisionsQuery.select(selectedCountryID));


    }

    /** This method will be called whenever we want to convey information to the user
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

    /** Initialize method. This method populates the combo box
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ModCustomerCountryComboBox.setItems(CountriesQuery.populateCountryTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            ModCustomerStateProvinceComboBox.setItems(DivisionsQuery.populateDivisionTable());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}
