package uicontrollers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import businessLogic.BlFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import ui.MainGUI;

public class UserGUIController implements Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup Idioms;

    @FXML
    private Button ShowMoveBtn;

    @FXML
    private Button browseBtn;

    @FXML
    private Button depositBtn;

    @FXML
    private Button placeBetBtn;

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    @FXML
    void browseClick() {
        mainGUI.showBrowseQuestions();
        System.out.println("as");
    }

    @FXML
    void depositClick() {
        mainGUI.showDeposit();
    }

    @FXML
    void placeClick() {
        mainGUI.showPlace();
    }

    @FXML
    void showMovements() {mainGUI.showMovements(); }

    public UserGUIController(BlFacade bl) {
        businessLogic = bl;
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @FXML
    void putCastellano(javafx.event.ActionEvent event) {
        Locale.setDefault(new Locale("es"));
        System.out.println("Locale: " + Locale.getDefault());
        redraw();
    }

    @FXML
    void putEnglish(javafx.event.ActionEvent event) {
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
    private void redraw() {
        browseBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("BrowseQuestions"));
        placeBetBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("PlaceBet"));
        depositBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("DepositMoney"));
        ShowMoveBtn.setText(ResourceBundle.getBundle("Etiquetas").
                getString("ShowMovements"));

    }

}
