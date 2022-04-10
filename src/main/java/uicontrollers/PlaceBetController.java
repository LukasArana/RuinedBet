package uicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ui.MainGUI;

public class PlaceBetController implements Controller{

    @FXML
    private Button betBt;

    @FXML
    private Button btnClose;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableColumn<?, ?> ec1;

    @FXML
    private TableColumn<?, ?> ec2;

    @FXML
    private ComboBox<?> feeComboBox;

    @FXML
    private TableColumn<?, ?> qc1;

    @FXML
    private TableColumn<?, ?> qc2;

    @FXML
    private TextField stakeField;

    @FXML
    private TableView<?> tblEvents;

    @FXML
    private TableView<?> tblQuestions;

    @FXML
    private Label winningsLb;

    @FXML
    void closeClick(ActionEvent event) {

    }

    @FXML
    void placeBetClick(ActionEvent event) {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }
}
