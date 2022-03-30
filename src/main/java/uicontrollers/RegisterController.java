package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.MainGUI;

public class RegisterController implements Controller{

    private BlFacade businessLogic;

    private MainGUI mainGUI;

    @FXML
    private TextField ageField;

    @FXML
    private Button alreadyAccountBtn;

    @FXML
    private Label answrLbl;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usrField;

    public RegisterController(BlFacade bl) {
        businessLogic = bl;
    }

    @FXML
    void goToLogin(ActionEvent event) {
        mainGUI.showLogin();
    }

    @FXML
    void registerClick(ActionEvent event) {
        if(usrField.getText().equals("")){
            answrLbl.setText("You must enter a username");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(passField.getText().equals("")){
            answrLbl.setText("You must enter a password");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(nameField.getText().equals("")){
            answrLbl.setText("You must enter a name");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(emailField.getText().equals("")){
            answrLbl.setText("You must enter an email");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(surnameField.getText().equals("")){
            answrLbl.setText("You must enter a surname");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(ageField.getText().equals("")){
            answrLbl.setText("You must enter an age");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(!isInteger(ageField.getText())){
            answrLbl.setText("You must enter a valid age");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(Integer.parseInt(ageField.getText()) < 18){
            answrLbl.setText("You must be older than 18");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(!businessLogic.emailIsFree(emailField.getText())) {
            answrLbl.setText("Email is already in use.");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(!businessLogic.usernameIsFree(usrField.getText())){
            answrLbl.setText("Username is already in use.");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else{
            answrLbl.setText("You have successfully registered");
            answrLbl.getStyleClass().setAll("lbl","lbl-success");
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
