package model;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The type Question.
 */
public interface Question {

    String getQuestion();

    String getCorrect_ans();

    ArrayList<String> getAnswers();

    JSONObject toJson();
}
