package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Level;
import model.Question;
import util.GameManager;
import util.MapPainter;
import util.SceneSwitch;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionCtrl implements Initializable {

    Parent root;
    Stage stage;
    Scene scene;

    final ToggleGroup answerGroup = new ToggleGroup();


    private Question question;

    @FXML
    private Label quesTime;
    @FXML
    private Label levelLbl;
    @FXML
    private Label questionLbl;
    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private Button confirmBtn;

    public Question getQuestion() {
        return question;
    }


    public void setAnswer1(String text) {
        answer1.setUserData(text);
        answer1.setText(text);
    }

    public void setAnswer2(String text) {
        answer2.setUserData(text);
        answer2.setText(text);
    }

    public void setAnswer3(String text) {
        answer3.setUserData(text);
        answer3.setText(text);
    }

    public void setAnswer4(String text) {
        answer4.setUserData(text);
        answer4.setText(text);
    }

    public void setQuestion(Question question) {
        this.questionLbl.setText(question.getQuestion());
        this.question = question;
        setAnswer1(question.getAnswers().get(0));
        setAnswer2(question.getAnswers().get(1));
        setAnswer3(question.getAnswers().get(2));
        setAnswer4(question.getAnswers().get(3));
        levelLbl.setText(
                question.getLevel() == Level.EASY ? "Easy Question" :
                        question.getLevel() == Level.MEDIUM ? "Medium Question" : "Hard Question"
        );
    }

    @FXML
    void confirmClicked(ActionEvent event) {
        SceneSwitch.INSTANCE.exitQuestion();

        //correct answer
        if (answerGroup.getSelectedToggle().getUserData().equals(question.getCorrect_ans())) {
            Level level = question.getLevel();
            GameManager.INSTANCE.incScore(
                    level == Level.EASY ? 1 : level == Level.MEDIUM ? 2 : 3
            );
        } else {
            //wrong answer
            Level level = question.getLevel();
            GameManager.INSTANCE.decScore(
                    level == Level.EASY ? -10 : level == Level.MEDIUM ? -20 : -30
            );
            GameManager.INSTANCE.updateUi();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        questionLbl.setText(this.question.getQuestion());
        answer1.setToggleGroup(answerGroup);
        answer2.setToggleGroup(answerGroup);
        answer3.setToggleGroup(answerGroup);
        answer4.setToggleGroup(answerGroup);

    }
}