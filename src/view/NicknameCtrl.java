package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.SceneSwitch;

import javax.xml.soap.Text;
import java.io.IOException;

public class NicknameCtrl {

    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    TextField nicknameTxt;

    @FXML
    private void startClicked(ActionEvent event) throws IOException {
        SceneSwitch.INSTANCE.switchToGameLevelZero(nicknameTxt.getText());
    }

    @FXML
    private void cancelClicked(ActionEvent event) throws IOException {
        // Close popup
        // currently it is a separate window.
//        this.stage.close();


        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}