package controller;


import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Level;
import model.Question;
import model.SysData;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Alerts {

    private final SysData sysData = new SysData();

    public static void generateSuccessAlert(String titleText, String headerText, String ContentText) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(ContentText);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(mainPageController.class.getResourceAsStream("/style/pics/success.png")));
        alert.showAndWait();

    }

    public static void generateErrorAlert(Exception e, String title, String headerText, String ContentText) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.setContentText(ContentText);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        javafx.scene.control.Label label = new javafx.scene.control.Label("The exception stacktrace was:");
        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(mainPageController.class.getResourceAsStream("/style/pics/stop.png")));
        alert.showAndWait();

    }

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
                    Question newQuestion = new Question(questionText.getText(),
                            new ArrayList(Arrays.asList(answer1Text.getText(), answer2Text.getText(), answer3Text.getText(), answer4Text.getText())),
                            answer1Text.getText(), question.getLevel(), "Hawk");

                    // remove the old instance
                    if (newQuestion.getLevel() == Level.EASY) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getEasyQuestions();
                        Integer oldIndex = currentQuestions.indexOf(question);
                        currentQuestions.remove(question);
                        // add the new instance
                        currentQuestions.add(oldIndex, newQuestion);
                        sysData.setEasyQuestions(currentQuestions);

                    }
                    if (newQuestion.getLevel() == Level.MEDIUM) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getMedQuestions();
                        Integer oldIndex = currentQuestions.indexOf(question);
                        currentQuestions.remove(question);
                        // add the new instance
                        currentQuestions.add(oldIndex, newQuestion);
                        sysData.setMedQuestions(currentQuestions);

                    }
                    if (newQuestion.getLevel() == Level.HARD) {
                        ArrayList<Question> currentQuestions = SysData.getInstance().getHardquestions();
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
        Optional<Question> result = dialog.showAndWait();

//        TilePane r = new TilePane();
//        TextInputDialog td = new TextInputDialog("enter any text");
//        // setHeaderText
//        td.setHeaderText("enter your name");
//        // create a button
//        Button d = new Button("click");
//        // create a event handler
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                // show the text input dialog
//                td.show();
//            }
//        };
//
//        // set on action of event
//        d.setOnAction(event);
//
//        // add button and label
//        r.getChildren().add(d);
//        td.on
//td.show();
    }

    public static void generateDeleteConflictionErrorAlert(Exception e, String objName) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("deleting warning");
        alert.setHeaderText(objName + " was not deleted");
        alert.setContentText(
                "you can not delete this object since it is related to other data in the data base -  The DELETE statement conflicted with the REFERENCE constraint");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        javafx.scene.control.Label label = new javafx.scene.control.Label("The exception stacktrace was:");
        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(mainPageController.class.getResourceAsStream("/style/pics/stop.png")));
        alert.showAndWait();

    }

    public static void generateWarningAlert(String titleText, String headerText, String contentText) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(mainPageController.class.getResourceAsStream("/style/pics/warning.png")));
        alert.showAndWait();
    }

}