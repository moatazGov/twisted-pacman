package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameData;
import model.SysData;
import util.SceneSwitch;

import java.awt.*;
import java.io.IOException;
import java.util.List;

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
    private void exit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void initialize() {
        gameOver.setText("Game Over");
        gameOver.setFill(Color.WHITE);
        gameOver.setY(gameOver.getY() + 50);
        gameOver.setStyle("-fx-font: 30 arial;");

        nickName.setText(this.getNickName());
        nickName.setFill(Color.WHITE);
        nickName.setY(gameOver.getY() + 50);
        nickName.setStyle("-fx-font: 30 arial;");

        result.setText(this.getStatus());
        result.setFill(Color.WHITE);
        result.setY(gameOver.getY() + 100);
        result.setStyle("-fx-font: 30 arial;");


        score.setText(this.getScoreText());
        score.setFill(Color.WHITE);
        score.setY(gameOver.getY() + 150);
        score.setStyle("-fx-font: 30 arial;");
        System.out.println("second");
    }

    /**
     * The title shown on the screen.
     */
    @FXML private Text gameOver;

    /**
     * The title shown on the screen.
     */
    @FXML private Text score;

    /**
     * The title shown on the screen.
     */
    @FXML private Text nickName;
    /**
     * The title shown on the screen.
     */
    @FXML private Text result;


    @FXML
    private void startClicked(ActionEvent event) throws IOException {
        SceneSwitch.INSTANCE.switchToGameLevelZero(nickName.getText());
    }

    @FXML
    private void cancelClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}