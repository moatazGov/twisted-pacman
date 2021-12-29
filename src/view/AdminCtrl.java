package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static constant.FileName.VIEW_HOME;

public class AdminCtrl {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/add-question.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/edit-question.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/delete-question.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(VIEW_HOME));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
