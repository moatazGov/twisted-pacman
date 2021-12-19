package model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Question.
 */
public class Question  {
//    private String id;
    private String question;
    private ArrayList<String> answers;
    private String correct_ans;
    private Level level;
    private String team;

    /**
     * Instantiates a new Question with the given parameters.
     *
//     * @param id            the id
     * @param question      the question
     * @param answers       the answers
     * @param correct_ans the correct answer
     * @param level         the level
     */
    public Question( String question, ArrayList<String> answers, String correct_ans, Level level, String team) {
//        this.id = id;
        this.question = question;
        this.answers = answers;
        this.correct_ans = correct_ans;
        this.level = level;
        this.team = team;
    }

    public Question( String question, String correct_ans, Level level, String team) {
//        this.id = id;
        this.question = question;
        this.correct_ans = correct_ans;
        this.level = level;
        this.team = team;
    }

    /**
     * Instantiates a new Question with empty fields.
     */
    public Question() {

    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Question)) return false;
//        Question question = (Question) o;
//        return getId().equals(question.getId());
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }

    public void fromJson(JSONObject obj) {
        JSONArray answers1 = (JSONArray) obj.get("answers");
        ArrayList answersList = new ArrayList();

        for (Object answer : answers1) {
            answersList.add(((String) answer));
        }
        question = obj.get("question").toString();
        correct_ans = obj.get("correct_ans").toString();
        level = obj.get("level").toString().equals("3") ? Level.HARD : obj.get("level").toString().equals("1") ? Level.EASY : Level.MEDIUM;
        answers = answersList;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("question", question);
        obj.put("correct_ans", correct_ans);
        obj.put("level", level == Level.EASY ? "1" : level == Level.HARD ? "3" : "2");
        obj.put("answers", answers);
        return obj;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
//    public String getId() {
//        return id;
//    }
//
//    /**
//     * Sets id.
//     *
//     * @param id the id
//     */
//    public void setId(String id) {
//        this.id = id;
//    }

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
    public String getCorrect_ans() {
        return correct_ans;
    }

    /**
     * Sets correct answer.
     *
     * @param correct_ans the correct answer
     */
    public void setCorrect_ans(String correct_ans) {
        this.correct_ans = correct_ans;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(String team) {
        this.team = team;
    }
}
