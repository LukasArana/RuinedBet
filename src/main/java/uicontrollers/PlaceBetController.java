package uicontrollers;

import businessLogic.BlFacade;
import domain.Event;
import domain.Question;
import domain.fee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.util.Callback;
import ui.MainGUI;
import utils.Dates;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class PlaceBetController implements Controller{

    @FXML
    private Button betBt;

    @FXML
    private Button btnClose;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableColumn<Event, Integer> ec1;

    @FXML
    private TableColumn<Event, String> ec2;

    @FXML
    private ComboBox<fee> feeComboBox;

    @FXML
    private TableColumn<Question, Integer> qc1;

    @FXML
    private TableColumn<Question, String> qc2;

    @FXML
    private TextField stakeField;

    @FXML
    private TableView<Event> tblEvents;

    @FXML
    private TableView<Question> tblQuestions;

    @FXML
    private Label winningsLb;

    @FXML
    private Label messageLbl;

    private MainGUI mainGUI;
    private BlFacade businessLogic;
    private List<LocalDate> holidays = new ArrayList<>();
    private ObservableList<fee> fees;

    public PlaceBetController(BlFacade bl) {
        businessLogic = bl;
    }


    @FXML
    void closeClick(ActionEvent event) {
        mainGUI.showMain();
    }

    @FXML
    void placeBetClick(ActionEvent event) {
        messageLbl.getStyleClass().clear();
        if(stakeField.getText().equals("")) {
            messageLbl.setText("You must introduce the stake to bet");
            messageLbl.getStyleClass().setAll("lbl", "lbl-danger");
        }
        if (Float.parseFloat(stakeField.getText()) >= 10){
            messageLbl.setText("You don't have enough money to place the bet");
            messageLbl.getStyleClass().setAll("lbl", "lbl-danger");
        }
        else{
            messageLbl.setText("Bet placed correctly");
            messageLbl.getStyleClass().setAll("lbl","lbl-success");
            stakeField.setText("");
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI=mainGUI;
    }

    private void setEvents(int year, int month) {

        Date date = Dates.toDate(year, month);

        for (Date day : businessLogic.getEventsMonth(date)) {
            holidays.add(Dates.convertToLocalDateViaInstant(day));
        }
    }

    private void setEventsPrePost(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        setEvents(date.getYear(), date.getMonth().getValue());
        setEvents(date.plusMonths(1).getYear(), date.plusMonths(1).getMonth().getValue());
        setEvents(date.plusMonths(-1).getYear(), date.plusMonths(-1).getMonth().getValue());
    }

    @FXML
    void initialize(){
        datepicker.setOnMouseClicked(e -> {
            DatePickerSkin skin = (DatePickerSkin) datepicker.getSkin();
            skin.getPopupContent().lookupAll(".button").forEach(node -> {
                node.setOnMouseClicked(event -> {
                    List<Node> labels = skin.getPopupContent().lookupAll(".label").stream().toList();
                    String month = ((Label) (labels.get(0))).getText();
                    String year = ((Label) (labels.get(1))).getText();
                    YearMonth ym = Dates.getYearMonth(month + " " + year);
                    setEventsPrePost(ym.getYear(), ym.getMonthValue());
                });
            });


        });

        datepicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            if (holidays.contains(item)) {
                                this.setStyle("-fx-background-color: pink");
                            }
                        }
                    }
                };
            }
        });

        datepicker.setOnAction(actionEvent -> {
            tblEvents.getItems().clear();
            tblQuestions.getItems().clear();
            messageLbl.setText("");
            messageLbl.getStyleClass().clear();
            Vector<Event> events= businessLogic.getEvents(Dates.convertToDate(datepicker.getValue()));
            for(Event e:events){
                tblEvents.getItems().add(e);
            }
        });

        tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
            if(newselection != null){
                tblQuestions.getItems().clear();
                Vector<Question> questions = tblEvents.getSelectionModel().getSelectedItem().getQuestions();
                for(Question q:questions){
                    tblQuestions.getItems().add(q);
                }
            }
        });

        qc1.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        qc2.setCellValueFactory(new PropertyValueFactory<>("question"));

        ec1.setCellValueFactory(new PropertyValueFactory<>("eventNumber"));
        ec2.setCellValueFactory(new PropertyValueFactory<>("description"));

        tblQuestions.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
            if(newselection != null){
                feeComboBox.getItems().clear();
                fees = FXCollections.observableArrayList(new ArrayList<>());
                fees.setAll(tblQuestions.getSelectionModel().getSelectedItem().getFeeList());
                //fees = tblQuestions.getSelectionModel().getSelectedItem().getFeeList();
                feeComboBox.setItems(fees);
                if(feeComboBox.getItems().size() == 0){
                    betBt.setDisable(true);
                    messageLbl.setText("There are no fees for this question");
                    messageLbl.getStyleClass().setAll("lbl", "lbl-danger");
                }else {
                    betBt.setDisable(false);
                    messageLbl.setText("");
                    messageLbl.getStyleClass().clear();

                }

            }

        });

        //float winnings = feeComboBox.getSelectionModel().getSelectedItem().getFee()*Float.parseFloat (stakeField.getText());
        //winningsLb.setText(String.valueOf(winnings));

    }
}
