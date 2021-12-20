//package tests;
//
//import java.util.ArrayList;
//import model.GameData;
//import model.Level;
//import model.SysData;
//import model.Question;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SysDataTest {
//
//    /*
//     * creating manual games list based on info from json file
//     * comparing expected games list based on json with actual list created with loadGames function.
//     */
//    @org.junit.jupiter.api.Test
//    void loadGamesJson() {
//        SysData test = SysData.getInstance();
//        GameData game = new GameData("yosi", 32);
//        ArrayList<GameData> games = new ArrayList<>();
//        games.add(game);
//      assertEquals(test.loadGamesJson(), games);
//
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void loadQuestionsJson() {
//        SysData test = SysData.getInstance();
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("Help contain a consistent programming style.");
//        answers.add("Promise system structure degradation.");
//        answers.add("Reduce record errors, and improve development methods.");
//        answers.add("Data errors, duplication and inconsistency.");
//        Question question = new Question("What are code reviews good for?",answers, "3", Level.HARD, null);
//        ArrayList<Question> questions = new ArrayList<>();
//        questions.add(question);
//        assertEquals(test.loadQuestionsJson().get(1), questions.get(0));
//
//
//    }
//
//
//    /*
//     * creating manual players list based on info from json file
//     * comparing expected players list based on json with actual list created with loadPlayers function.
//     */
//   /* @org.junit.jupiter.api.Test
//    void loadPlayersJson() {
//        SysData test = new SysData();
//        ArrayList<String> games = new ArrayList<>();
//
//        games.add("moataz");
//        games.add("oday");
//        games.add("samar");
//        games.add("yvonne");
//        assertEquals(games, test.loadPlayersJson());
//    }
//*/
//
//    /*
//     * checking if exporting player list into a json file is successful.
//     */
//  /*
//    @org.junit.jupiter.api.Test
//    void savePlayers() {
//        SysData test = new SysData();
//        assertTrue(test.savePlayers());
//    }
//*/
//    /*
//     * checking if exporting games list into a json file is successful.
//     */
//    @org.junit.jupiter.api.Test
//    void saveGames() {
//
//        SysData test = SysData.getInstance();
//        assertTrue(test.saveGames());
//    }
//
//    /*
//     * checking if exporting questions list into a json file is successful.
//     */
//    @org.junit.jupiter.api.Test
//    void saveQuestions()
//    {
//        SysData test = SysData.getInstance();
//        assertTrue(test.saveQuestions());
//    }
//}