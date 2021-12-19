package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class SysData {
    private static SysData instance;
    private ArrayList<Score> scores;
    private ArrayList<GameData> games;
    private ArrayList<Question> questions;
    private ArrayList<String> players;
    //todo add to class diagram
    private String nickName;

    public static SysData getInstance() {

        if (instance == null) {
            instance = new SysData();
            if (instance.load())
                return instance;
            else
                return null;
        }

        return instance;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public ArrayList<GameData> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameData> games) {
        this.games = games;
    }

    public ArrayList<Question> getQuestions() {
        load();
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Starts the game and launches UI.
     *
     * @return true if game started successfully false if an error occurred
     */
    boolean start() {
        return true;
    }

    /**
     * loads the data from local DB
     *
     * @return true if system data was loaded successfully, false if an error occurred
     */
    public boolean load() {
        try {
//            games = loadGamesJson();
//            players = loadPlayersJson();
            questions = loadQuestionsJson();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Saves the system data to local DB
     *
     * @return true if system data was saved successfully, false if an error occurred
     */
    public boolean save() {
        try {
            saveGames();
            savePlayers();
            saveQuestions();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * adds the question to local DB
     *
     * @param question
     * @return true if question was added successfuly and false if an error occurred
     */
    boolean addQuestion(Question question) {


        return true;
    }

    /**
     * removes the question from local DB
     *
     * @param question
     * @return true if question was removed successfully and false if an error occurred
     */
    boolean removeQuestion(Question question) {
        return true;
    }


    /**
     * gets the actual question from the system data
     *
     * @param question
     * @return the question similar to the given question from the system data if exists
     * and a question with empty fields otherwise
     */
    Question getQuestion(Question question) {
        return new Question();
    }

    /**
     * edits the question with similar id to the given question and replaces it with the given question's data
     *
     * @param question
     * @return true if the question was edited successfully, false if an error Occurred
     */
    Question editQuestion(Question question) {
        return new Question();
    }

    /**
     * adds the given game data to the games history in local DB
     *
     * @param game
     * @return true if the game was added successfully, false if an error occurred
     */
    boolean addGame(GameData game) {
        return true;
    }


    public ArrayList loadGamesJson() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<GameData> games = new ArrayList<>();
//        try (FileReader reader = new FileReader("src/resources/json/games.json")) {
//
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray gamesList = (JSONArray) (((JSONObject) obj).get("games"));
//            for (Object game : gamesList) {
//                GameData newGame = new GameData();
//                newGame.fromJson((JSONObject) game);
//                games.add(newGame);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return games;
    }

    private ArrayList loadQuestionsJson() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<Question> questions = new ArrayList<>();

        try (FileReader reader = new FileReader("src/resources/json/questions.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray questionsList = (JSONArray) ((JSONObject) obj).get("questions");
            for (Object question : questionsList) {
                Question newQuestion = new Question();
                newQuestion.fromJson((JSONObject) question);
                questions.add(newQuestion);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return questions;

    }


//    private ArrayList loadPlayersJson() {
//        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//        ArrayList<String> players = new ArrayList<>();
//        try (FileReader reader = new FileReader("src/resources/json/BANANA.json")) {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray questionsList = (JSONArray) obj;
//            for (Object player : questionsList) {
//                players.add(String.valueOf(((JSONObject) player).get("nickName")));
//            }
//            System.out.println(questionsList);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return questions;
//    }

    private boolean savePlayers() {
        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/json/employees.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            JSONObject json = new JSONObject();
            json.put("players", players);
            file.write(json.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean saveGames() {
//Write JSON file
        try (FileWriter file = new FileWriter("src/resources/json/games.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            JSONObject json = new JSONObject();
            json.put("games", games);
            file.write(json.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveQuestions() {
        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/json/questions.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            JSONObject json = new JSONObject();
            JSONArray jsonQuestions = new JSONArray();
            for (Question question : questions){
                jsonQuestions.add(question.toJson());
            }
            json.put("questions", jsonQuestions);
            file.write(json.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
