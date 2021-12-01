package com.hawks.twistedPacman.View;

import com.hawks.twistedPacman.Model.Difficulty;
import com.hawks.twistedPacman.Model.Question;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;
import javafx.util.Callback;
//import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionsAdminCtrl implements Initializable {

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

    private void fillTable() {
                tvObservableList.addAll(
                        new Question("1", "abc", "1", Difficulty.EASY),
                        new Question("2", "ques2","2", Difficulty.HARD),
                        new Question("3", "que3", "3", Difficulty.HARD),
                        new Question("4", "ques4", "2", Difficulty.MEDIUM),
                        new Question("5", "ques5", "4", Difficulty.EASY));
    }


//    public void fillRooms() {
//
//        ObservableList<Question> questions;
//
//        questions = FXCollections.observableArrayList();
//
//        try {
//
//            for (Question q : DataAccessObject.getRooms(cruiseShip)) {
//                questions.add(q);
//            }
//        } catch (Exception e) {
//            Alerts.generateErrorAlert(e, "Exception", "Exception Has Occurred", "Could not load rooms from DataBase!");
//
//        }
//
//        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
//        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("bedsAmount"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
//        questionsTbl.setItems(questions);
//
//        if (questionsTbl.getColumns().size() == 4) {
//
//            TableColumn<Question, Void> deleteCol = new TableColumn<Question, Void>("Action");
//            deleteCol.setPrefWidth(80);
//            deleteCol.setMinWidth(80);
//            deleteCol.setMaxWidth(80);
//
//            Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
//                @Override
//                public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
//                    final TableCell<Question, Void> cell = new TableCell<Question, Void>() {
//
//                        private final JFXButton deleteBtn = new JFXButton();
//                        private final JFXButton updateBtn = new JFXButton();
//                        private final HBox pane = new HBox(updateBtn, deleteBtn);
//
//                        {
//                            deleteBtn.getStyleClass().add("deleteBtn");
//                            updateBtn.getStyleClass().add("updateBtn");
//
//                            updateBtn.setOnAction((ActionEvent event) -> {
//                                Room data = getTableView().getItems().get(getIndex());
//
//                                System.out.println("update : " + data);
//
//                            });
//
//                            deleteBtn.setOnAction((ActionEvent event) -> {
//                                Room data = getTableView().getItems().get(getIndex());
//
//                                try {
//                                    DataAccessObject.deleteRoom(data);
//                                    roomsTable.getColumns().remove(deleteCol);
//                                    fillRooms();
//
//                                    Alerts.generateSuccessAlert("transaction completed",
//                                            "Data was deleted successfully",
//                                            "Room " + data.getRoomNumber() + " was deleted from the data Base ");
//                                    mainPageController.getInstance().fillDashBoard();
//
//                                } catch (SQLException e) {
//
//                                    if (e.getErrorCode() == 547) {
//
//                                        Alerts.generateErrorAlert(e, "deleting warning", "room was not deleted",
//                                                "you can not delete this object since it is related to other data in the data base -  The DELETE statement conflicted with the REFERENCE constraint");
//
//                                    } else
//                                        Alerts.generateErrorAlert(e, "Exception", "Exception Has Occurred",
//                                                "Could not load rooms from DataBase!");
//
//                                }
//
//                            });
//
//                        }
//
//                        @Override
//                        protected void updateItem(Void item, boolean empty) {
//                            super.updateItem(item, empty);
//
//                            setGraphic(empty ? null : pane);
//                        }
//                    };
//                    return cell;
//                }
//            };
//
//            deleteCol.setCellFactory(cellFactory);
//            roomsTable.getColumns().add(deleteCol);
//        }
//    }

    /**
     * adds questions to list of question and to JSON file
     * @param event
     * @throws IOException
     */
    @FXML
    private void addClicked(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/fxml/home-view.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    /**
     * back button returns the user to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        addButtonToTable();
        setTableappearance();

        fillTable();
        questionsTbl.setItems(tvObservableList);


    }
}
