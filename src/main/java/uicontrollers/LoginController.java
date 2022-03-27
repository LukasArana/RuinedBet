package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.MainGUI;

public class LoginController implements Controller{

    @FXML
    private TextArea answrField;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passField;

    @FXML
    private Button registerBtn;

    private MainGUI mainGUI;

    @FXML
    private TextField usrField;

    private BlFacade businessLogic;

    public LoginController(BlFacade bl) {
        businessLogic = bl;
    }

    @FXML
    void loginAction(ActionEvent event) {
        if(usrField.getText().equals("")){
            answrField.setText("You must enter a username");
        }
        else if(passField.getText().equals("")){
            answrField.setText("You must enter a password");
        }
        else{
            mainGUI.showMain();
        }
    }

    @FXML
    void registerAction(ActionEvent event) {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
