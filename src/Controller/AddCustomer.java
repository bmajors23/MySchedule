package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField AddCustomerAddressTxtField;

    @FXML
    private Button AddCustomerCancBtn;

    @FXML
    private ComboBox<?> AddCustomerCountryComboBox;

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
    private ComboBox<?> AddCustomerStateProvinceComboBox;

    @FXML
    void OnActionDisplayCustomerMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionSaveCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
