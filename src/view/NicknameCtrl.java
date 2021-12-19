package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.SceneSwitch;

import java.io.IOException;

public class NicknameCtrl {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private void startClicked(ActionEvent event) throws IOException {
        SceneSwitch.INSTANCE.switchToGameLevelZero();
    }

    @FXML
    private void cancelClicked(ActionEvent event) throws IOException {
        // Close popup
        // currently it is a separate window.
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}