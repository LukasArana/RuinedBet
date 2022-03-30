package uicontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.MainGUI;

public class setFeesController implements Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label answerLbl;

    @FXML
    private Button closeBtn;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TableColumn<Event, String> eventTxt;

    @FXML
    private TableColumn<Event, Integer> eventId;

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TextField feeField;

    @FXML
    private TableColumn<Question, Integer> questionId;

    @FXML
    private TableView<Question> questionTable;

    @FXML
    private TableColumn<Question, String> questionTxt;

    @FXML
    private TextField resultField;

    @FXML
    private Button setFeeBtn;

    private BlFacade businessLogic;

    private MainGUI mainGUI;

    private ObservableList questionData;

    private ObservableList eventData;

    public setFeesController(BlFacade bl) {
        businessLogic = bl;
    }

    @FXML
    void closeClick(ActionEvent event) {
        mainGUI.showMain();
    }

    @FXML
    void setFeeClick(ActionEvent event) {
        if(dateSelector.getValue() == null){
            answerLbl.setText("You must select a date");
            answerLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(eventTable.getSelectionModel().getSelectedItem() == null){
            answerLbl.setText("You must select an event");
            answerLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(questionTable.getSelectionModel().getSelectedItem() == null){
            answerLbl.setText("You must select a question");
            answerLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(resultField.getText().equals("")){
            answerLbl.setText("You must enter a result");
            answerLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        else if(feeField.getText().equals("")){
            answerLbl.setText("You must select a fee");
            answerLbl.getStyleClass().setAll("lbl","lbl-danger");
        }
        //else if(businessLogic.feeExists(answerLbl.getText(),(String) questionTableModel.getValueAt(j,1))) {
        //    answerLbl.setText("You must select another result.");
        //}
    }

    @FXML
    void initialize() {
        questionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        questionTxt.setCellValueFactory(new PropertyValueFactory<>("text"));

        eventId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventTxt.setCellValueFactory(new PropertyValueFactory<>("text"));

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

}




