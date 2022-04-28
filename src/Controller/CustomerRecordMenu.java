package Controller;

import Model.Customer;
import Model.SavedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TableColumn<Customer, Integer> CustomerRecordMenuTblCountryCol;

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

    @FXML
    void OnActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void OnActionDisplayAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
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
    void OnActionDisplayModCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerRecordMenuTbl.setItems(SavedData.getAllCustomers());

        CustomerRecordMenuTblCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        CustomerRecordMenuTblCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        CustomerRecordMenuTblAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        CustomerRecordsMenuTblPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerZip"));
        CustomerRecordsMenuTblPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        CustomerRecordMenuTblStateCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

    }
}
