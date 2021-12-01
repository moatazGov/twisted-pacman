package com.hawks.twistedPacman.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InstructionsCtrl implements Initializable {

    Parent root;
    Stage stage;
    Scene scene;


    @FXML
    private Label genLbl;
    @FXML
    private Label arrowsLbl;
    @FXML
    private Label spaceLbl;
    @FXML
    private TextField bombTxt;
    @FXML
    private TextField ques1Txt;
    @FXML
    private TextField ques2Txt;
    @FXML
    private TextField ques3Txt;


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

        genLbl.setText("The point of the game is to eat as much points, without getting eaten\n" +
                "by the ghosts. \nThe game ends when the player gets 200 points," +
                "or loses all their lives.");
        arrowsLbl.setText("Use the arrows to move\n" +
                "around the board");
        spaceLbl.setText("Press the space bar to blow up\n" +
                "the bomb after eating it");
        bombTxt.setText("Bomb. After eating it you can blow it up to kill a ghost.");
        ques1Txt.setText("Easy question. When answered correctly grants +1 point, otherwise -10.");
        ques2Txt.setText("Moderate question. When answered correctly grants +2 point, otherwise -20.");
        ques3Txt.setText("Hard question. When answered correctly grants +3 point, otherwise -30.");


        bombTxt.setEditable(false);
        ques1Txt.setEditable(false);
        ques2Txt.setEditable(false);
        ques3Txt.setEditable(false);
    }
}
