package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.SceneSwitch;

public class NicknameCtrl {
    @FXML
    private TextField nicknameTxt;

    @FXML
    private void startClicked(ActionEvent event) {

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
    private void cancelClicked(ActionEvent event) {
        SceneSwitch.INSTANCE.switchToHome();
    }

}