package com.hawks.twistedPacman.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeCtrl {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button playBtn;
    private Button adminBtn;
    private Button exitBtn;
    private Button instructionsBtn;
    private Button seeMoreBtn;

    @FXML
    private void playClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("nickname.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void adminClicked(ActionEvent event) {

    }

    @FXML
    private void exitClicked(ActionEvent event) {

    }

    @FXML
    private void instructionsClicked(ActionEvent event) {

    }

    @FXML
    private void seeMoreClicked(ActionEvent event) {

    }
}

