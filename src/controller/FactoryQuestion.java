package controller;

import constant.Level;
import model.EasyQuestion;
import model.HardQuestion;
import model.MediumQuestion;
import model.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class FactoryQuestion {

 /**
     * A method to create three similar objects with a little difference, depending on the level the method creates a suitable object.
     *
     * @param questionJson
     * @return
     */
    public Question getQuestion(JSONObject questionJson) {
        JSONArray answers1 = (JSONArray) questionJson.get("answers");
        ArrayList answersList = new ArrayList();
        Level level = questionJson.get("level").toString().equals("3") ? Level.HARD : questionJson.get("level").toString().equals("1") ? Level.EASY : Level.MEDIUM;
        String question = questionJson.get("question").toString();
        String correct_ans = questionJson.get("correct_ans").toString();

        for (Object answer : answers1) {
            answersList.add(answer);
        }

        if (level == Level.EASY) {
            return new EasyQuestion(question, answersList, correct_ans);
        }
        if (level == Level.MEDIUM) {
            return new MediumQuestion(question, answersList, correct_ans);
        }
        if (level == Level.HARD) {
            return new HardQuestion(question, answersList, correct_ans);
        }

        return null;
    }


}
