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
    private TextField nicknameTxt;

    @FXML
    private void startClicked(ActionEvent event) throws IOException {

        try {
            if (nicknameTxt.getText().length() != 0) {
                    try {
                        SceneSwitch.INSTANCE.switchToGameLevelZero(nicknameTxt.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            } else {
                MsgBox.display("Error!!", "Please insert a nickname.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void cancelClicked(ActionEvent event) throws IOException {
        SceneSwitch.INSTANCE.switchToHome();
    }

}