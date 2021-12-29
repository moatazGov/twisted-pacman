package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Question.
 */
public class HardQuestion implements Question {
    String question;
    ArrayList<String> answers;
    String correct_ans;
    /**
     * Instantiates a new Question with the given parameters.
     *
//     * @param id            the id
     * @param question      the question
     * @param answers       the answers
     * @param correct_ans the correct answer
     */
    public HardQuestion(String question, ArrayList<String> answers, String correct_ans) {
        this.question = question;
        this.answers = answers;
        this.correct_ans = correct_ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EasyQuestion)) return false;
        EasyQuestion question1 = (EasyQuestion) o;
        return Objects.equals(question, question1.question) &&
                Objects.equals(answers, question1.answers) &&
                Objects.equals(correct_ans, question1.correct_ans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answers, correct_ans);
    }

    @Override
    public void fromJson(JSONObject obj) {
        JSONArray answers1 = (JSONArray) obj.get("answers");
        ArrayList answersList = new ArrayList();

        for (Object answer : answers1) {
            answersList.add(((String) answer));
        }
        question = obj.get("question").toString();
        correct_ans = obj.get("correct_ans").toString();
        answers = answersList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("question", question);
        obj.put("correct_ans", correct_ans);
        obj.put("level", "3");
        obj.put("answers", answers);
        return obj;
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
     * Gets answers.
     *
     * @return the question
     */
    @Override
    public ArrayList getAnswers() {
        return answers;
    }


    /**
     * Gets correctAnswer.
     *
     * @return the question
     */
    @Override
    public String getCorrect_ans() {
        return correct_ans;
    }

    /**
     * Sets question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }


}
