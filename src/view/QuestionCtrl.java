package view;

import constant.Level;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.EasyQuestion;
import model.MediumQuestion;
import model.Question;
import util.SceneSwitch;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionCtrl implements Initializable {
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

    public void setQuestion(Question question) {
        this.questionLbl.setText(question.getQuestion());
        this.question = question;
        setAnswer1(question.getAnswers().get(0));
        setAnswer2(question.getAnswers().get(1));
        setAnswer3(question.getAnswers().get(2));
        setAnswer4(question.getAnswers().get(3));
        levelLbl.setText(
                question instanceof EasyQuestion ? "Easy Question" :
                        question instanceof MediumQuestion ? "Medium Question" : "Hard Question"
        );
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

    @FXML
    void confirmClicked(ActionEvent event) {
        SceneSwitch.INSTANCE.exitQuestion();
        Level questionLevel = question instanceof EasyQuestion ? Level.EASY : question instanceof MediumQuestion ? Level.MEDIUM : Level.HARD;
        //correct answer
        if (answerGroup.getSelectedToggle().getUserData().equals(question.getCorrect_ans())) {
            Controller.INSTANCE.incScore(
                    questionLevel == Level.EASY ? 1 : questionLevel == Level.MEDIUM ? 2 : 3

            );
        } else {
            //wrong answer
            Controller.INSTANCE.decScore(
                    questionLevel == Level.EASY ? -10 : questionLevel == Level.MEDIUM ? -20 : -30
            );
            Controller.INSTANCE.updateUi();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answer1.setToggleGroup(answerGroup);
        answer2.setToggleGroup(answerGroup);
        answer3.setToggleGroup(answerGroup);
        answer4.setToggleGroup(answerGroup);

    }
}