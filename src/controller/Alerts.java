package controller;


import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Alerts {


    /**
     * method to show Success dialog window when finishing the game/
     */
    public static void generateSuccessAlert(String titleText, String headerText, String ContentText) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(ContentText);
        alert.showAndWait();

    }

    /**
     * in Admin Panel, access to update (making changes) questions
     *
     * @param question
     */
    public static void generateUpdateQuestionAlert(Question question) {
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Update Question ");
        dialog.setResizable(true);

        Label questionLbl = new Label("question: ");
        Label answer1Lbl = new Label("correct answer: ");
        Label answer2Lbl = new Label("answer 1: ");
        Label answer3Lbl = new Label("answer 2: ");
        Label answer4Lbl = new Label("answer 3: ");
        TextField questionText = new TextField(question.getQuestion());
        TextField answer1Text = new TextField(question.getCorrect_ans());
        question.getAnswers().remove(question.getCorrect_ans());
        TextField answer2Text = new TextField(question.getAnswers().get(0));
        TextField answer3Text = new TextField(question.getAnswers().get(1));
        TextField answer4Text = new TextField(question.getAnswers().get(2));
        question.getAnswers().add(question.getCorrect_ans());

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, Question>() {
            @Override
            public Question call(ButtonType b) {
                // load system data
                SysData sysData = new SysData();
                sysData.load();
                if (b == buttonTypeOk) {
                    // generate the new question to add to the right array
                    FactoryQuestion factory = new FactoryQuestion();
                    JSONObject object = new JSONObject();
                    object.put("question", questionText.getText());
                    object.put("correct_ans", answer1Text.getText());
                    object.put("level", question instanceof EasyQuestion ? "1" : question instanceof MediumQuestion ? "2" : "3");
                    object.put("answers", new ArrayList(Arrays.asList(answer1Text.getText(), answer2Text.getText(), answer3Text.getText(), answer4Text.getText())));
                    Question newQuestion = factory.getQuestion(object);
                    // remove the old instance
                    if (newQuestion instanceof EasyQuestion) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getEasyQuestions();
                        Integer oldIndex = currentQuestions.indexOf(question);
                        currentQuestions.remove(question);
                        // add the new instance
                        currentQuestions.add(oldIndex, newQuestion);
                        sysData.setEasyQuestions(currentQuestions);
                    }
                    if (newQuestion instanceof MediumQuestion) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getMedQuestions();
                        Integer oldIndex = currentQuestions.indexOf(question);
                        currentQuestions.remove(question);
                        // add the new instance
                        currentQuestions.add(oldIndex, newQuestion);
                        sysData.setMedQuestions(currentQuestions);
                    }
                    if (newQuestion instanceof HardQuestion) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getHardQuestions();
                        Integer oldIndex = currentQuestions.indexOf(question);
                        currentQuestions.remove(question);
                        // add the new instance
                        currentQuestions.add(oldIndex, newQuestion);
                        sysData.setHardQuestions(currentQuestions);
                    }

                    sysData.save();
                    return newQuestion;
                }
                return null;
            }
        });
        GridPane grid = new GridPane();
        grid.add(questionLbl, 1, 1);
        grid.add(answer1Lbl, 1, 3);
        grid.add(answer2Lbl, 1, 5);
        grid.add(answer3Lbl, 1, 7);
        grid.add(answer4Lbl, 1, 9);

        grid.add(questionText, 1, 2);
        grid.add(answer1Text, 1, 4);
        grid.add(answer2Text, 1, 6);
        grid.add(answer3Text, 1, 8);
        grid.add(answer4Text, 1, 10);
        dialog.getDialogPane().setContent(grid);

    }


}