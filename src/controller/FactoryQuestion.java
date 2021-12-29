package controller;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class FactoryQuestion {

    SysData systemData = new SysData();

    /**
     * a flag to determine if questions were parsed or not
     */
    public Question getQuestion(JSONObject questionJson) {
        JSONArray answers1 = (JSONArray) questionJson.get("answers");
        ArrayList answersList = new ArrayList();
        Level level = questionJson.get("level").toString().equals("3") ? Level.HARD : questionJson.get("level").toString().equals("1") ? Level.EASY : Level.MEDIUM;
        String question = questionJson.get("question").toString();
        String correct_ans = questionJson.get("correct_ans").toString();

        for (Object answer : answers1) {
            answersList.add(((String) answer));
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
