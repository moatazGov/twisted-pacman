package Model;

import Controller.Controller;

import java.util.ArrayList;

/**
 *
 */
public class SysData {
    private Controller controller;
    private ArrayList<Score> scores;
    private ArrayList<Question> questions;
    //todo add to class diagram
    private String nickName;


    /**
     * Starts the game and launches UI.
     * @return true if game started successfully false if an error occurred
     */
    boolean start(){
        return true;
    }

    /**
     * loads the data from local DB
     * @return true if system data was loaded successfully, false if an error occurred
     */
    boolean load(){
        return true;
    }

    /**
     * Saves the system data to local DB
     * @return true if system data was saved successfully, false if an error occurred
     */
    boolean save(){
        return true;
    }

    /**
     * adds the question to local DB
     * @param question
     * @return true if question was added successfuly and false if an error occurred
     */
    boolean addQuestion(Question question){
        return true;
    }

    /**
     * removes the question from local DB
     * @param question
     * @return true if question was removed successfully and false if an error occurred
     */
    boolean removeQuestion(Question question){
        return true;
    }


    /**
     * gets the actual question from the system data
     * @param question
     * @return the question similar to the given question from the system data if exists
     * and a question with empty fields otherwise
     */
    Question getQuestion(Question question){
        return new Question();
    }

    /**
     * edits the question with similar id to the given question and replaces it with the given question's data
     * @param question
     * @return true if the question was edited successfully, false if an error Occurred
     */
    Question editQuestion(Question question){
        return new Question();
    }

    /**
     * adds the given game data to the games history in local DB
     * @param game
     * @return true if the game was added successfully, false if an error occurred
     */
    boolean addGame(GameData game){
        return true;
    }

}
