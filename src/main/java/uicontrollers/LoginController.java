package uicontrollers;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.MainGUI;

public class LoginController implements Controller{

    @FXML
    private Label answrLbl;

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
//        if(usrField.getText().equals("")){
//            answrLbl.setText("You must enter a username");
//            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
//        }
//        else if(passField.getText().equals("")){
//            answrLbl.setText("You must enter a password");
//            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
//        }
//        else{
//            mainGUI.showMain();
//        }

        if (usrField.getText().isBlank() || passField.getText().isBlank()) { //No values in text fields
            answrLbl.setText("Please insert valid username and passwords");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
            passField.setText("");
        }
        else if (!businessLogic.checkLogIn(usrField.getText(), passField.getText())) {
            answrLbl.setText("Not valid credentials, please try again");
            answrLbl.getStyleClass().setAll("lbl","lbl-danger");
            passField.setText("");
        } else { //Valid credentials
            if (businessLogic.isAdmin(usrField.getText())) { //open admin gui
                mainGUI.showMain();
            } else { //open user gui
                mainGUI.showMain();
            }
        }
    }


    @FXML
    void registerAction(ActionEvent event) {
        mainGUI.showRegister();
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
