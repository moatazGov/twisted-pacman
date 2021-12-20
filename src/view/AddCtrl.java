package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Difficulty;
import model.Level;
import model.Question;
import model.SysData;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddCtrl implements Initializable {

    Parent root;
    Stage stage;
    Scene scene;

    final ToggleGroup difficultyGroup = new ToggleGroup();
    final ToggleGroup answerGroup = new ToggleGroup();


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
                if(selectedRadioButton == firstRadio){
                    answerNum = "1";
                    answerTxt = firstAnswer.getText();
                }
                if(selectedRadioButton == secondRadio){
                    answerNum = "2";
                    answerTxt = secondAnswer.getText();
                }
                if(selectedRadioButton == thirdRadio){
                    answerNum = "3";
                    answerTxt = thirdAnswer.getText();
                }
                if(selectedRadioButton == fourthRadio){
                    answerNum = "4";
                    answerTxt = fourthAnswer.getText();
                }


                ArrayList<Question> current = new ArrayList<>();
                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.EASY)
                    current = SysData.getInstance().getEasyQuestions();
                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.MEDIUM)
                    current = SysData.getInstance().getMedQuestions();
                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.HARD)
                    current = SysData.getInstance().getHardquestions();
                current.add(new Question(
                        questionText.getText(),
                        new ArrayList<String>(Arrays.asList(secondAnswer.getText(), thirdAnswer.getText(), fourthAnswer.getText())),
                        answerNum,
                        (Level) difficultyGroup.getSelectedToggle().getUserData(),
                        "Hawk"
                ));

                System.out.println((Level) difficultyGroup.getUserData());

                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.EASY)
                    SysData.getInstance().setEasyQuestions(current);
                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.MEDIUM)
                    SysData.getInstance().setMedQuestions(current);
                if((Level) difficultyGroup.getSelectedToggle().getUserData() == Level.HARD)
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
