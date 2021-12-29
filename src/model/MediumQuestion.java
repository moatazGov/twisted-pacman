package model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * An object that represents a medium level class.
 */
public class MediumQuestion implements Question {
    String question;
    ArrayList<String> answers;
    String correct_ans;

    /**
     * Instantiates a new Question with the given parameters.
     *
     * @param question    the question
     * @param answers     the answers
     * @param correct_ans the correct answer
     */
    public MediumQuestion(String question, ArrayList<String> answers, String correct_ans) {

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

    /**
     * parse a json object representing the question object.
     *
     * @return
     */
    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("question", question);
        obj.put("correct_ans", correct_ans);
        obj.put("level", "2");
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
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrect_ans() {
        return correct_ans;
    }
}
