package view;

import constant.Level;
import controller.FactoryQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Question;
import model.SysData;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddCtrl implements Initializable {

    final ToggleGroup difficultyGroup = new ToggleGroup();
    final ToggleGroup answerGroup = new ToggleGroup();
    Parent root;
    Stage stage;
    Scene scene;
    @FXML
    private Button addBtn;
    @FXML
    private Button backBtn;
    @FXML
    private RadioButton easyRadio;
    @FXML
    private RadioButton mediumRadio;
    @FXML
    private RadioButton hardRadio;
    @FXML
    private RadioButton firstRadio;
    @FXML
    private RadioButton secondRadio;
    @FXML
    private RadioButton thirdRadio;
    @FXML
    private RadioButton fourthRadio;
    @FXML
    private TextArea questionText;
    @FXML
    private TextField firstAnswer;
    @FXML
    private TextField secondAnswer;
    @FXML
    private TextField thirdAnswer;
    @FXML
    private TextField fourthAnswer;


    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        try {
            if ((questionText.getText().length() != 0) && (firstAnswer.getText().length() != 0) && (secondAnswer.getText().length() != 0) && (thirdAnswer.getText().length() != 0)
                    && (fourthAnswer.getText().length() != 0) && (answerGroup.getSelectedToggle() != null) && (difficultyGroup.getSelectedToggle() != null)) {

                String answerNum = null;
                String answerTxt = null;
                RadioButton selectedRadioButton = (RadioButton) answerGroup.getSelectedToggle();

                /*
                    since the answers are displayed in a textfield, they are not connected to the radiobuttons.
                    set the correctAns as the chosen radio button.
                */
                if (selectedRadioButton == firstRadio) {
                    answerNum = "1";
                    answerTxt = firstAnswer.getText();
                }
                if (selectedRadioButton == secondRadio) {
                    answerNum = "2";
                    answerTxt = secondAnswer.getText();
                }
                if (selectedRadioButton == thirdRadio) {
                    answerNum = "3";
                    answerTxt = thirdAnswer.getText();
                }
                if (selectedRadioButton == fourthRadio) {
                    answerNum = "4";
                    answerTxt = fourthAnswer.getText();
                }


                ArrayList<Question> current = new ArrayList<>();
                Level level = (Level) difficultyGroup.getSelectedToggle().getUserData();
                String levelText = "";
                if (level == Level.EASY) {
                    levelText = "1";
                    current = SysData.getInstance().getEasyQuestions();
                }
                if (level == Level.MEDIUM) {
                    levelText = "2";
                    current = SysData.getInstance().getMedQuestions();
                }
                if (level == Level.HARD) {
                    levelText = "3";
                    current = SysData.getInstance().getHardQuestions();
                }


                FactoryQuestion factory = new FactoryQuestion();
                JSONObject object = new JSONObject();
                object.put("question", questionText.getText());
                object.put("correct_ans", firstAnswer.getText());
                object.put("level", levelText);
                object.put("answers", new ArrayList(Arrays.asList(firstAnswer.getText(), secondAnswer.getText(), thirdAnswer.getText(), fourthAnswer.getText())));
                Question newQuestion = factory.getQuestion(object);
                current.add(newQuestion);

                System.out.println((Level) difficultyGroup.getUserData());

                if (level == Level.EASY)
                    SysData.getInstance().setEasyQuestions(current);
                if (level == Level.MEDIUM)
                    SysData.getInstance().setMedQuestions(current);
                if (level == Level.HARD)
                    SysData.getInstance().setHardQuestions(current);

                SysData.getInstance().save();
                MsgBox.display("Confirmation", "Question added successfully!");

                questionText.clear();
                firstAnswer.clear();
                secondAnswer.clear();
                thirdAnswer.clear();
                fourthAnswer.clear();
                answerGroup.getSelectedToggle().setSelected(false);
                difficultyGroup.getSelectedToggle().setSelected(false);

            } else {
                MsgBox.display("Error", "Please make sure you\'ve filled in all fields.");
            }
        } catch (Exception e) {
            System.out.println("caught error while adding new question.");
        }
    }

    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/questionsAdmin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // grouping radio buttons together, in order to choose only one at a time.
        easyRadio.setUserData(Level.EASY);
        mediumRadio.setUserData(Level.MEDIUM);
        hardRadio.setUserData(Level.HARD);

        easyRadio.setToggleGroup(difficultyGroup);
        mediumRadio.setToggleGroup(difficultyGroup);
        hardRadio.setToggleGroup(difficultyGroup);

        firstRadio.setToggleGroup(answerGroup);
        secondRadio.setToggleGroup(answerGroup);
        thirdRadio.setToggleGroup(answerGroup);
        fourthRadio.setToggleGroup(answerGroup);
    }

}
