package com.hawks.twistedPacman.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCtrl {

    Parent root;
    Stage stage;
    Scene scene;

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

}
