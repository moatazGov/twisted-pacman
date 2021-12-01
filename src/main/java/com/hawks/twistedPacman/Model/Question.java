package com.hawks.twistedPacman.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The type Question.
 */
public class Question {
    private String id;
    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;
    private Difficulty difficulty;

    /**
     * Instantiates a new Question with the given parameters.
     *
     * @param id            the id
     * @param question      the question
     * @param answers       the answers
     * @param correctAnswer the correct answer
     * @param difficulty    the difficulty
     */
    public Question(String id, String question, ArrayList<String> answers, String correctAnswer, Difficulty difficulty) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }

    public Question(String id, String question, String correctAnswer, Difficulty difficulty) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }

    /**
     * Instantiates a new Question with empty fields.
     */
    public Question() {

    }

    public void fromJson(JSONObject obj) {
        JSONArray answers1 = (JSONArray) obj.get("answers");
        ArrayList answersList = new ArrayList();

        for (Object answer : answers1) {
            answersList.add(((String) answer));
        }
        id = obj.get("id").toString();
        question = obj.get("question").toString();
        correctAnswer = obj.get("correctAnswer").toString();
        difficulty = obj.get("difficulty").toString() == "hard" ? Difficulty.HARD : obj.get("difficulty").toString() == "easy" ? Difficulty.EASY : Difficulty.MEDIUM;
        answers = answersList;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("question", question);
        obj.put("correctAnswer", correctAnswer);
        obj.put("difficulty", difficulty == Difficulty.EASY ? "easy" : difficulty == Difficulty.HARD ? "hard" : "medium");
        obj.put("answers", answers);
        return obj;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets answers.
     *
     * @return the answers
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * Sets answers.
     *
     * @param answers the answers
     */
    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    /**
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets correct answer.
     *
     * @param correctAnswer the correct answer
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
