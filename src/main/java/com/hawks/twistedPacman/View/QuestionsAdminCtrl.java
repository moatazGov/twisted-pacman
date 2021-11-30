package com.hawks.twistedPacman.View;

import com.hawks.twistedPacman.Model.Difficulty;
import com.hawks.twistedPacman.Model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class QuestionsAdminCtrl {

    Parent root;
    Stage stage;
    Scene scene;


    @FXML
    private TableView<Question> questionsTbl;
    @FXML
    private TableColumn<Question, String> colAns;
    @FXML
    private TableColumn<Question, Void> colBtns;
    @FXML
    private TableColumn<Question, Difficulty> colDiff;
    @FXML
    private TableColumn<Question, String> colID;
    @FXML
    private TableColumn<Question, String> colQues;

    private void addButtonToTable() {
        TableColumn<Question, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
            @Override
            public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
                final TableCell<Question, Void> cell = new TableCell<Question, Void>() {

                    private final Button editBtn = new Button("Edit");
                    {
                        editBtn.setOnAction((ActionEvent event) -> {
//                            Question question = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedData: " + question);
                        });
                    }

                    private final Button deleteBtn = new Button("Delete");
                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
//                            Question question = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedData: " + question);
                        });
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editBtn);
                            setGraphic(deleteBtn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        questionsTbl.getColumns().add(colBtns);

    }

    /**
     * adds questions to list of question + JSON file
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

}
