package uicontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.BlFacade;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import ui.MainGUI;

public class DepositMoneyController implements Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBut;

    @FXML
    private Label currentBalance;

    @FXML
    private Button depositBut;

    @FXML
    private TextField depositField;

    @FXML
    private Label newBalance;

    @FXML
    private Label outputLabel;
    private MainGUI mainGUI;
    private BlFacade businessLogic;

    @FXML
    void backPressed(ActionEvent event) { mainGUI.showMain();}
    @FXML
    void depositUpdate(InputMethodEvent event) {
        System.out.println("a" + businessLogic.getCurrency(mainGUI.getUsername()).toString());
        currentBalance.setText(businessLogic.getCurrency(mainGUI.getUsername()).toString());
    }

    @FXML
    void depositPressed(ActionEvent event) {

        float deposit = 0;
        try {
            deposit = Float.parseFloat(depositField.getText());
        } catch (NumberFormatException e) {
            outputLabel.setText("Please insert a valid number");
        }

        if (deposit > 0 ){
            businessLogic.updateCurrency(deposit, mainGUI.getUsername());
        } else{
            outputLabel.setText("Please insert a valid value to currency");
        }
    }
    public DepositMoneyController(BlFacade bl) {
        businessLogic = bl;

    }

    @FXML
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;

    }
}
