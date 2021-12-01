package com.hawks.twistedPacman.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCtrl implements Initializable{

    Parent root;
    Stage stage;
    Scene scene;

    final ToggleGroup difficultyGroup = new ToggleGroup();
    final ToggleGroup answerGroup = new ToggleGroup();


    @FXML
    private Button addBtn;
    @FXML
    private Button backBtn;
    @FXML
    private RadioButton easyRadio;
    @FXML
    private RadioButton mediumRadio;
    @FXML
    private RadioButton hardRadio;
    @FXML
    private RadioButton firstRadio;
    @FXML
    private RadioButton secondRadio;
    @FXML
    private RadioButton thirdRadio;
    @FXML
    private RadioButton fourthRadio;




    @FXML
    private void addClicked(ActionEvent event) throws IOException {
    }

    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // grouping radio buttons together, in order to choose only one at a time.
        easyRadio.setToggleGroup(difficultyGroup);
        mediumRadio.setToggleGroup(difficultyGroup);
        hardRadio.setToggleGroup(difficultyGroup);

        firstRadio.setToggleGroup(answerGroup);
        secondRadio.setToggleGroup(answerGroup);
        thirdRadio.setToggleGroup(answerGroup);
        fourthRadio.setToggleGroup(answerGroup);
    }

}
