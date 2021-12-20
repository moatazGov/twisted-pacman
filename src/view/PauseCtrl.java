package view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.SceneSwitch;

import javafx.scene.input.KeyEvent;
import java.io.IOException;

public class PauseCtrl {

    @FXML
    private Button homeButt;

    @FXML
    private void startClicked(ActionEvent event) throws IOException {
        SceneSwitch.INSTANCE.switchToHome();
        SceneSwitch.INSTANCE.returnToGame();
    }
    @FXML
    public void KeyPressed(KeyEvent e)
    {
        switch(e.getCode())
        {
            case ESCAPE:
                SceneSwitch.INSTANCE.returnToGame();
                break;

        }
    }
}
