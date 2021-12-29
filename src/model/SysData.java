package model;

import controller.FactoryQuestion;
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
 *  Class to hold the system data.
 */
public class SysData {
    private boolean isInitialized = false;
    private static SysData instance;
    private ArrayList<GameData> games;
    private ArrayList<Question> easyQuestions = new ArrayList<>();
    private ArrayList<Question> hardQuestions = new ArrayList<>();
    private ArrayList<Question> medQuestions = new ArrayList<>();
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

    public ArrayList<GameData> getGames() {
        return games;
    }

    public ArrayList<Question> getEasyQuestions() {
        return easyQuestions;
    }

    public void setEasyQuestions(ArrayList<Question> easyQuestions) {
        this.easyQuestions = easyQuestions;
    }

    public ArrayList<Question> getHardQuestions() {
        return hardQuestions;
    }

    public void setHardQuestions(ArrayList<Question> hardquestions) {
        this.hardQuestions = hardquestions;
    }

    public ArrayList<Question> getMedQuestions() {
        return medQuestions;
    }

    public void setMedQuestions(ArrayList<Question> medquestions) {
        this.medQuestions = medquestions;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * loads the data from local DB
     *
     * @return true if system data was loaded successfully, false if an error occurred
     */
    public boolean load() {
        try {
            if(!isInitialized) {
                isInitialized = true;
                games = loadGamesJson();
                loadQuestionsJson();
                return true;
            }
            return false;
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
            saveQuestions();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<GameData> loadGamesJson() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<GameData> games = new ArrayList<>();
        try (FileReader reader = new FileReader("src/resources/json/games.json")) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray gamesList = (JSONArray) (((JSONObject) obj).get("games"));
            for (Object game : gamesList) {
                GameData newGame = new GameData();
                newGame.fromJson((JSONObject) game);
                games.add(newGame);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

    private void loadQuestionsJson() {
        FactoryQuestion factory = new FactoryQuestion();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        easyQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();
        medQuestions = new ArrayList<>();
        try (FileReader reader = new FileReader("src/resources/json/questions.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray questionsList = (JSONArray) ((JSONObject) obj).get("questions");
            for (Object question : questionsList) {
                Question newQuestion = factory.getQuestion((JSONObject) question);

                if (newQuestion instanceof EasyQuestion) {
                    easyQuestions.add(newQuestion);
                }
                if (newQuestion instanceof HardQuestion) {
                    hardQuestions.add(newQuestion);

                }
                if (newQuestion instanceof MediumQuestion) {
                    medQuestions.add(newQuestion);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public boolean saveGames() {
        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/json/games.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            JSONObject json = new JSONObject();
            JSONArray jsonGames = new JSONArray();

            for (GameData game : games){
                jsonGames.add(game.toJson());
            }
            json.put("games", jsonGames);
            file.write(json.toJSONString());
            file.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveQuestions() {
        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/json/questions.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            JSONObject json = new JSONObject();
            JSONArray jsonQuestions = new JSONArray();
            for (Question question : easyQuestions) {
                jsonQuestions.add(question.toJson());
            }
            for (Question question : medQuestions) {
                jsonQuestions.add(question.toJson());
            }
            for (Question question : hardQuestions) {
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
