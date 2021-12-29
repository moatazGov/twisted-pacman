package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameData;
import model.SysData;
import util.SceneSwitch;

import java.io.IOException;
import java.util.List;

import static constant.FileName.VIEW_HOME;

public class EndGameCtrl {

    Parent root;
    Stage stage;
    Scene scene;

    private String nickNameText;
    private String statusText;
    private String scoreText;
    private SysData sysData = new SysData();


    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.score.setText(scoreText);
    }

    public String getNickName() {
        return nickNameText;
    }

    public void setNickName(String nickName) {
        this.nickName.setText(nickName);
    }

    public String getStatus() {
        return statusText;
    }

    public void setStatus(String status) {
        this.result.setText(status);
    }


    @FXML
    public void initialize() {
        gameOver.setText("Game Over");
        gameOver.setStyle("-fx-text-fill: #FFFD00;");

        nickName.setText(this.getNickName());
        nickName.setStyle("-fx-text-fill: #FFFD00;");

        result.setText(this.getStatus());
        result.setStyle("-fx-text-fill: #FFFD00;");


        score.setText(this.getScoreText());
        score.setStyle("-fx-text-fill: #FFFD00;");
        System.out.println("second");
    }

    /**
     * The title shown on the screen.
     */
    @FXML
    private Label gameOver;

    /**
     * The title shown on the screen.
     */
    @FXML
    private Label score;

    /**
     * The title shown on the screen.
     */
    @FXML
    private Label nickName;
    /**
     * The title shown on the screen.
     */
    @FXML
    private Label result;

    /**
     * The button to return to the main menu
     */
    @FXML
    private Button mainBtn;

    @FXML
    private void exit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(VIEW_HOME));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}