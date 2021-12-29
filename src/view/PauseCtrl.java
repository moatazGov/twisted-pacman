package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import util.SceneSwitch;

public class PauseCtrl {

    @FXML
    private Button mainBtn;

    @FXML
    private void mainMenuClicked(ActionEvent event) {
        SceneSwitch.INSTANCE.switchToHome();
        SceneSwitch.INSTANCE.returnToGame();
    }

    @FXML
    public void KeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                SceneSwitch.INSTANCE.returnToGame();
                break;

        }
    }
}
