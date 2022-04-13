package uicontrollers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import businessLogic.BlFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Movement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.MainGUI;


public class ShowMovementsController implements Controller{

    private MainGUI mainGUI;

    ObservableList<Movement> data;

    private BlFacade businessLogic;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Movement, LocalDate> dateColumn;

    @FXML
    private TableColumn<Movement, String> balanceColumn;

    @FXML
    private TableColumn<Movement, String> eventColumn;

    @FXML
    private TableView<Movement> operationTable;

    @FXML
    void goBack(ActionEvent event) {mainGUI.showMain();}

    @FXML
    void initialize() {
       /* assert eventColumn != null : "fx:id=\"eventColumn\" was not injected: check your FXML file 'showMovements.fxml'.";
        assert operationColumn != null : "fx:id=\"operationColumn\" was not injected: check your FXML file 'showMovements.fxml'.";
        assert operationTable != null : "fx:id=\"operationTable\" was not injected: check your FXML file 'showMovements.fxml'.";
        */
        operationTable.getItems().clear();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));

        LocalDate now = LocalDate.now();

        data = FXCollections.observableArrayList();
        for (Movement m: data.toArray(new Movement[0])) {
            data.add(m);
        }
        data.addAll(
            new Movement(now, "Barça-Athletic", "+15"),
            new Movement(now, "real-osasuna", "-5")
        );
        operationTable.getItems().addAll(data);

        setupOperationSelection();
    }

    private void setupOperationSelection(){
        operationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(operationTable.getSelectionModel().getSelectedItem());
            }
        });
    }

    public ShowMovementsController(BlFacade bl) {
        businessLogic = bl;
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

}
