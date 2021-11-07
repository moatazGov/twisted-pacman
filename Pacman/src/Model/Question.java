package Model;

import java.util.ArrayList;

/**
 * The type Question.
 */
public class Question {
    private int id;
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
    public Question(int id, String question, ArrayList<String> answers, String correctAnswer, Difficulty difficulty) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }

    /**
     * Instantiates a new Question with empty fields.
     */
    public Question() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
