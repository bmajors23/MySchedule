package Controller;

import Helper.JDBC;
import Model.Customer;
import Model.SavedData;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public static void dialogBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
