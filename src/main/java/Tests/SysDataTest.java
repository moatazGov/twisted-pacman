package Tests;

import static org.junit.jupiter.api.Assertions.*;


import com.hawks.twistedPacman.Model.Difficulty;
import com.hawks.twistedPacman.Model.GameData;
import com.hawks.twistedPacman.Model.Question;
import com.hawks.twistedPacman.Model.SysData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SysDataTest {

    /*
    * creating manual games list based on info from json file
    * comparing expected games list based on json with actual list created with loadGames function.
     */
    @org.junit.jupiter.api.Test
    void loadGamesJson() {
        SysData test = new SysData();
        ArrayList<GameData> games = new ArrayList<>();
        GameData game = new GameData(1,"moataz", 23);
        GameData game2 = new GameData(2,"yvonen", 34);
        GameData game3 = new GameData(3,"samar", 54);
        GameData game4 = new GameData(4,"oday", 34);
        games.add(game);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        assertEquals(games, test.loadGamesJson());


    }


    /*
     * creating manual players list based on info from json file
     * comparing expected players list based on json with actual list created with loadPlayers function.
     */
    @org.junit.jupiter.api.Test
    void loadPlayersJson() {
        SysData test = new SysData();
        ArrayList<String> games = new ArrayList<>();

        games.add("moataz");
        games.add("oday");
        games.add("samar");
        games.add("yvonne");
        assertEquals(games, test.loadPlayersJson());
    }


    /*
     * checking if exporting player list into a json file is successful.
     */
    @org.junit.jupiter.api.Test
    void savePlayers() {
        SysData test = new SysData();
        assertTrue(test.savePlayers());
    }

    /*
     * checking if exporting games list into a json file is successful.
     */
    @org.junit.jupiter.api.Test
    void saveGames() {

        SysData test = new SysData();
        assertTrue(test.saveGames());
    }

    /*
     * checking if exporting questions list into a json file is successful.
     */
    @org.junit.jupiter.api.Test
    void saveQuestions()
    {
        SysData test = new SysData();
        assertTrue(test.saveQuestions());
    }
}