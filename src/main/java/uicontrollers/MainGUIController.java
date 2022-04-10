package uicontrollers;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import ui.MainGUI;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainGUIController implements Controller{

    @FXML
    private Label selectOptionLbl;

    @FXML
    private Button browseQuestionsBtn;

    @FXML
    private Button createQuestionBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private MainGUI mainGUI;

    @FXML
    private Button setFeesBtn;

    @FXML
    private RadioButton englishRB;

    @FXML
    private RadioButton euskaraRB;

    @FXML
    private RadioButton castellanoRB;

    @FXML
    private ToggleGroup g1;

    @FXML
    private Button createEventBtn;

    @FXML
    private Button showBalancebtn;

    @FXML
    void userLogIn(ActionEvent event){ mainGUI.showUser();}

    @FXML
    void browseQuestions(ActionEvent event) {
        mainGUI.showBrowseQuestions();
    }

    @FXML
    void createQuestion(ActionEvent event) {
        mainGUI.showCreateQuestion();
    }

    @FXML
    void setFeesClick(ActionEvent event){mainGUI.showSetFees();}

    @FXML
    void createEvent(ActionEvent event) {mainGUI.showCreateEvents();}

    @FXML
    void showBalance(ActionEvent event) {mainGUI.showBalance();}

    @FXML
    void putCastellano(ActionEvent event) {
        Locale.setDefault(new Locale("es"));
        System.out.println("Locale: " + Locale.getDefault());
        redraw();
    }

    @FXML
    void putEnglish(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        System.out.println("Locale: " + Locale.getDefault());
        redraw();
    }

    @FXML
    void putEuskara(ActionEvent event) {
        Locale.setDefault(new Locale("eus"));
        System.out.println("Locale: " + Locale.getDefault());
        redraw();
    }

    @FXML
    void showMovements(ActionEvent event){mainGUI.showMovements();}


    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    private void redraw() {
        selectOptionLbl.setText(ResourceBundle.getBundle("Etiquetas").
                getString("SelectUseCase"));
        browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("BrowseQuestions"));
        createQuestionBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("CreateQuestion"));
        setFeesBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("SetFee"));
        createEventBtn.setText(ResourceBundle.getBundle("Etiquetas").
               getString("CreateEvent"));

        //this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
    }
}
