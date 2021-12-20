package view;

import controller.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Level;
import model.Question;
import model.SysData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class QuestionsAdminCtrl implements Initializable {

    ObservableList<Question> questions = FXCollections.observableArrayList();

    SysData sysData = new SysData();
    Parent root;
    Stage stage;
    Scene scene;


    @FXML
    private TableView<Question> questionsTbl;
    @FXML
    private TableColumn<Question, String> colAns;
    @FXML
    private TableColumn<Question, Level> colDiff;
//    @FXML
//    private TableColumn<Question, String> colID;
    @FXML
    private TableColumn<Question, String> colQues;

    private void setTableappearance() {
        questionsTbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void createTable() {
        colQues.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        colAns.setCellValueFactory(new PropertyValueFactory<Question, String>("correct_ans"));
        colDiff.setCellValueFactory(new PropertyValueFactory<Question, Level>("level"));
        questionsTbl.setItems(questions);
    }

    /**
     * Fills the table with the  questions loaded from the json.
     */
    public void fillQuestions() {
        // load the question from json, fill observable object with data and
        SysData sysData = SysData.getInstance();
        sysData.load();
        ArrayList<Question> questionsData = sysData.getEasyQuestions();
        questionsData.addAll(sysData.getMedQuestions());
        questionsData.addAll(sysData.getHardquestions());
        questions.addAll(questionsData);
        createTable();

        if (questionsTbl.getColumns().size() == 3) {
            // prevent unnecessary columns from being created?
            TableColumn<Question, Void> deleteCol = new TableColumn<Question, Void>("Action");
            deleteCol.setPrefWidth(80);
            deleteCol.setMinWidth(80);
            deleteCol.setMaxWidth(80);

            Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
                @Override
                public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
                    final TableCell<Question, Void> cell = new TableCell<Question, Void>() {
                        private final Button deleteBtn = new Button();
                        private final Button updateBtn = new Button();
                        private final HBox pane = new HBox(updateBtn, deleteBtn);

                        {
                            deleteBtn.getStyleClass().add("deleteBtn");
                            updateBtn.getStyleClass().add("updateBtn");
                            deleteBtn.setText("delete");
                            updateBtn.setText("update");
                            updateBtn.setOnAction((ActionEvent event) -> {
                                try {
                                    Question data = getTableView().getItems().get(getIndex());
                                    Alerts.generateUpdateQuestionAlert(data);
                                    System.out.println("update : " + data);
                                    SysData.getInstance().load();
                                    ArrayList<Question> questionsData = sysData.getEasyQuestions();
                                    questionsData.addAll(sysData.getMedQuestions());
                                    questionsData.addAll(sysData.getHardquestions());
                                    questions.removeAll(questions.stream().collect(Collectors.toList()));
                                    questions.addAll(questionsData);
                                } catch (Exception e) {
                                    System.out.println("caught error : " + e.getMessage());
                                }
                            });
                            deleteBtn.setOnAction((ActionEvent event) -> {
                                Question data = getTableView().getItems().get(getIndex());
                                if (data.getLevel() == Level.EASY){
                                    questions.remove(data);
                                    SysData.getInstance().getEasyQuestions().remove(data);
                                    ArrayList<Question> questionsData = sysData.getEasyQuestions();
                                    sysData.setEasyQuestions(questionsData);
                                }
                                if (data.getLevel() == Level.MEDIUM){
                                    questions.remove(data);
                                    SysData.getInstance().getMedQuestions().remove(data);
                                    ArrayList<Question> questionsData = sysData.getMedQuestions();
                                    sysData.setMedQuestions(questionsData);
                                }
                                if (data.getLevel() == Level.HARD){
                                    questions.remove(data);
                                    SysData.getInstance().getHardquestions().remove(data);
                                    ArrayList<Question> questionsData = sysData.getHardquestions();
                                    sysData.setHardQuestions(questionsData);
                                }

                                sysData.save();
                                Alerts.generateSuccessAlert("Operation Successfully",
                                        "Question removed successfully !", "");
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            setGraphic(empty ? null : pane);
                        }
                    };
                    return cell;
                }
            };

            deleteCol.setCellFactory(cellFactory);
            questionsTbl.getColumns().add(deleteCol);
        }
    }

    /**
     * adds questions to list of question and to JSON file
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/add-question.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * back button returns the user to the main screen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableappearance();
        fillQuestions();
    }
}
