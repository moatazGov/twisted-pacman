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

public class AddCtrl implements Initializable{

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
        try{
            ArrayList<Question> current = SysData.getInstance().getQuestions();
            current.add(new Question(
                    questionText.getText(),
                    new ArrayList<String>(Arrays.asList(secondAnswer.getText(), thirdAnswer.getText(), fourthAnswer.getText())),
                    firstAnswer.getText(),
                    (Level) difficultyGroup.getUserData(),
                    "Hawk"
                    ));
            SysData.getInstance().setQuestions(current);
            SysData.getInstance().save();
        }catch (Exception e){System.out.println("caught error while adding new question.");}
    }

    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/admin.fxml"));
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
