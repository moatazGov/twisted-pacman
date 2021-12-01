package com.hawks.twistedPacman.View;

import com.hawks.twistedPacman.Controller.Alerts;
import com.hawks.twistedPacman.Model.Difficulty;
import com.hawks.twistedPacman.Model.Question;
import com.hawks.twistedPacman.Model.SysData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
//import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionsAdminCtrl implements Initializable {
    ObservableList<Question> questions = FXCollections.observableArrayList();

    SysData sysData = new SysData();
    Parent root;
    Stage stage;
    Scene scene;

    private final ObservableList<Question> tvObservableList = FXCollections.observableArrayList();


    @FXML
    private TableView<Question> questionsTbl;
    @FXML
    private TableColumn<Question, String> colAns;
    //    @FXML
//    private TableColumn<Question, Void> colBtns;
    @FXML
    private TableColumn<Question, Difficulty> colDiff;
    @FXML
    private TableColumn<Question, String> colID;
    @FXML
    private TableColumn<Question, String> colQues;

    private void setTableappearance() {
        questionsTbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void createTable() {
        colID.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
        colQues.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        colAns.setCellValueFactory(new PropertyValueFactory<Question, String>("correctAnswer"));
        colDiff.setCellValueFactory(new PropertyValueFactory<Question, Difficulty>("difficulty"));
        questionsTbl.setItems(questions);
    }

    /**
     * Fills the table with the  questions loaded from the json.
     */
    public void fillQuestions() {
        // load the question from json, fill observable object with data and
        ArrayList<Question> questionsData = sysData.getQuestions();
        questions.addAll(questionsData);
        createTable();

        if (questionsTbl.getColumns().size() == 4) {
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
                                try{
                                    Question data = getTableView().getItems().get(getIndex());
                                    Alerts.generateUpdateQuestionAlert(data);
                                    System.out.println("update : " + data);
                                    SysData.getInstance().load();
                                    ArrayList<Question> questionsData = sysData.getQuestions();
                                    questions.removeAll(questions.stream().toList());
                                    questions.addAll(questionsData);
                                }catch (Exception e){
                                    System.out.println("caught error : " + e.getMessage());
                                }
                            });
                            deleteBtn.setOnAction((ActionEvent event) -> {
                                Question data = getTableView().getItems().get(getIndex());
                                questions.remove(data);
                                ArrayList<Question> questionsData = (ArrayList<Question>) questions.stream().toList();
                                sysData.setQuestions(questionsData);
                                sysData.save();
                                Alerts.generateSuccessAlert("Operation Successfully",
                                        "Question removed successffully !", "");
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
        root = FXMLLoader.load(getClass().getResource("/fxml/add-question.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        root = FXMLLoader.load(getClass().getResource("/fxml/home-view.fxml"));
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
