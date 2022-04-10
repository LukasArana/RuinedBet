package uicontrollers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import jdk.jfr.Event;
import ui.MainGUI;
import utils.Dates;


public class ShowBalanceController implements Controller{

    ObservableList<Event> data;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Dates, Event> eventColumn;

    @FXML
    private TableColumn<Dates, Event> balanceColumn;

    @FXML
    private TableView<Event> operationTable;

    @FXML
    void initialize() {
        assert eventColumn != null : "fx:id=\"eventColumn\" was not injected: check your FXML file 'showBalance.fxml'.";
        //assert operationColumn != null : "fx:id=\"operationColumn\" was not injected: check your FXML file 'showBalance.fxml'.";
        assert operationTable != null : "fx:id=\"operationTable\" was not injected: check your FXML file 'showBalance.fxml'.";


        /*data = FXCollections.observableArrayList();

        data.addAll(
                new Event(1, "Barça-Madrid", new Date("2022-04-10"))
        );

        operationTable.getItems().addAll(data);
        */
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }
}
