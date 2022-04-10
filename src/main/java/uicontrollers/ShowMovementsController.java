package uicontrollers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domain.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utils.Dates;
import java.time.LocalDateTime;


public class ShowMovementsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Dates, Event> eventColumn;

    @FXML
    private TableColumn<Dates, Event> operationColumn;

    @FXML
    private TableView<Event> operationTable;

    @FXML
    void initialize() {
        assert eventColumn != null : "fx:id=\"eventColumn\" was not injected: check your FXML file 'showMovements.fxml'.";
        assert operationColumn != null : "fx:id=\"operationColumn\" was not injected: check your FXML file 'showMovements.fxml'.";
        assert operationTable != null : "fx:id=\"operationTable\" was not injected: check your FXML file 'showMovements.fxml'.";

        LocalDate now = LocalDate.now();
        //operationTable.getItems().add(new Event(1, "none", new Date));
    }

}
