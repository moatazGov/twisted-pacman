package util;

import constant.Direction;
import constant.GameLevel;
import constant.GameStatus;
import constant.PortalType;
import controller.FactoryQuestionGrid;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.*;
import sun.management.jdp.JdpGenericPacket;
import view.GameController;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * <h1>GameManager</h1>
 *
 * <p>A {@link GameManager} is an object of utility to manage the game status and process globally,
 * and reflect the realtime results in the UI.
 *
 * <p><b>Note:</b> this class is implemented an {@link Enum}, thus to be a singleton class.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    GameManager.INSTANCE.{ANY_METHOD_HERE}()
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @see GameController
 * @see GameStatus
 * @since 1.0
 */
public enum GameManager {
    /**
     * The shared instance for global use.
     */
    INSTANCE;

    private GameLevel currentLevel = GameLevel.ZERO;

    /**
     * The current playing {@link Map}.
     */
    private Map map;

    private int currentEasyIndex = 1;
    private int currentMedIndex = 1;
    private int currentHardIndex = 1;

    private  SysData sysData = new SysData();

    /**
     * The current {@link GameController}.
     */
    private GameController gameController;

    private Direction currentPacDirection;

    /**
     * The current {@link GameStatus}.
     */
    private GameStatus gameStatus;

    /**
     * The current {@link Life}.
     */
    private Life life;

    /**
     * The current {@link Score}.
     */
    private Score score;


    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
    public void incScore(int newScore) {
        this.score.gain(newScore);
    }
    public void decScore(int newScore) {
        this.score.gain(newScore);
    }

    /**
     * Initializes the game properties based on the given {@link Map} and updates UI via {@link
     * GameController}.
     *
     * @param map            the current {@link Map}
     * @param gameController the {@link GameController} linked to the current displayed UI
     */
    public void init(Map map, GameController gameController) {
        // add map
        this.map = map;

        // add game controller
        this.gameController = gameController;

        // init game status
        this.gameStatus = GameStatus.PAUSE;

        // init life
        this.life = new Life();

        // init score
        this.score = new Score(map.getNickname());

        // init UI
        this.initUi();
    }

    /**
     * Returns the current {@link GameStatus}.
     *
     * @return the current {@link GameStatus}
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Starts the game.
     *
     * <p>This method calls {@link #wakeUpGhosts()} and set the {@link #gameStatus} to {@link
     * GameStatus#START}.
     */
    public void startGame() {
        if (gameStatus == GameStatus.PAUSE) {
            wakeUpGhosts();
            gameStatus = GameStatus.START;
        }
    }

    /**
     * Pauses the game.
     *
     * <p>This method calls {@link #freezeGhosts()} and set the {@link #gameStatus} to {@link
     * GameStatus#PAUSE}.
     */
    public void pauseGame() {
        if(gameStatus == GameStatus.PAUSE)
        {
            SceneSwitch.INSTANCE.returnToGame();
        }

        freezeGhosts();
        map.getPacman().freeze();
        SceneSwitch.INSTANCE.switchToPause();
        gameStatus = GameStatus.PAUSE;

    }

    public void pauseGameNoPopUp() {
        if(gameStatus == GameStatus.PAUSE)
        {
            SceneSwitch.INSTANCE.returnToGame();
        }

        freezeGhosts();
        map.getPacman().freeze();
        gameStatus = GameStatus.PAUSE;

    }

    /**
     * Loses the game.
     *
     * <p>This method calls {@link #()}, {@link #calculateScore()} and {@link #navigateBack()}
     * to the Select scene.
     */
    public void loseGame() {
        if (getGameStatus() == GameStatus.START) {
            endGame(map.getNickname(),"LOST",String.valueOf(0));
            calculateScore();
//            navigateBack();
        }
    }

    /**
     * Wins the game.
     *
     * <p>This method calls {@link()}, {@link #calculateScore()} and {@link #navigateBack()}
     * to the Select scene.s
     */
    public void winGame() {
        if (getGameStatus() == GameStatus.START) {
            endGame(map.getNickname(), "WON", String.valueOf(this.score.getValue()));
//      calculateScore();
//      navigateBack();
        }
    }

    private boolean storeScore(String name, String status, String score){
        try {
            sysData.load();
            sysData.getGames().add(new GameData(0, name, Integer.valueOf(score)));
            sysData.save();
            return true;
        }catch (Exception e){
            System.out.println("caught error in store score");
            return false;
        }
    }


    /**
     * Ends the game.
     *
     * <p>This method calls {@link #freezeGhosts()} and set the {@link #gameStatus} to {@link
     * GameStatus#END}.
     */
    public void endGame(String name, String status, String score) {
        freezeGhosts();
        storeScore(name,status,score);
        SceneSwitch.INSTANCE.switchToEndGame(name, status, score);
        gameStatus = GameStatus.END;
    }

    /**
     * This method is called when any {@link Ghost} is touching the {@link Pacman}.
     * @param ghost the {@link Ghost} touching the {@link Pacman}
     */
    public void handleGhostTouched(Ghost ghost) {
        if (gameStatus == GameStatus.END) {
            return;
        }
        sendPacmanToSpawn();
        life.lose();
        score.lose(ghost.getValue());
        if (life.getRemaining() <= 0) {
            //MusicPlayer.INSTANCE.playDeath();
            loseGame();
        }
        updateUi();
    }

    /**
     * This method is called when any {@link model.PacItem} is touching the {@link model.Pacman}.
     *
     * @param cookie the {@link model.PacItem} touching the {@link model.Pacman}
     */
    public void handlePacItemTouched(PacItem cookie) {
        cookie.eat();
        checkWin(score.gain(cookie.getValue()));
        updateUi();
    }


    /**
     * shows question pop-up
    **/
    private boolean showQuestionPopUp(Question question){
        return true;
    }

    /**
     * generate a new question at a random grid
     */
    private boolean generateRandomQuestionGrid(QuestionGrid questionGrid){
        int index = 0;
        int wantedIndex = getRandomNumberUsingNextInt(0, map.getPacItems().size());
        // find random pacItem to replace and set visible value to false;
        PacItem randPacItem = null;
        for (PacItem pacItem : map.getPacItems()) {
            if(index == wantedIndex){
                randPacItem = pacItem;
            }
            index++;
        }
        if(randPacItem != null) {
            randPacItem.setVisible(false);
            questionGrid.setVisible(false);
            Double itemXTmp = randPacItem.getX();
            Double itemYTmp = randPacItem.getY();

            if(questionGrid.getQuestion().getLevel() == Level.EASY){
                questionGrid.setQuestion(SysData.getInstance().getEasyQuestions().get(currentEasyIndex));
                currentEasyIndex++;
            }
            if(questionGrid.getQuestion().getLevel() == Level.MEDIUM){
                questionGrid.setQuestion(SysData.getInstance().getMedQuestions().get(currentMedIndex));
                currentMedIndex++;
            }
            if(questionGrid.getQuestion().getLevel() == Level.HARD){
                questionGrid.setQuestion(SysData.getInstance().getHardquestions().get(currentHardIndex));
                currentHardIndex++;
            }

            randPacItem.setX(questionGrid.getX());
            randPacItem.setY(questionGrid.getY());

            Timer timer = new Timer();

            PacItem finalRandPacItem = randPacItem;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    questionGrid.setX(itemXTmp);
                    questionGrid.setY(itemYTmp);
                    finalRandPacItem.setVisible(true);
                    questionGrid.setVisible(true);
                }
            }, 10*1000);


            return true;
        }return false;
    }

    /**
     *
     * Generate random number in the given range.
     * @param min
     * @param max
     * @return
     */
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * handles pacman touching a question
     * @param questionGrid
     */
    public void handleQuestionGrid(QuestionGrid questionGrid) { //TODO
        pauseGameNoPopUp();
        generateRandomQuestionGrid(questionGrid);
        SceneSwitch.INSTANCE.switchToQuestion(questionGrid);
    }

    public void handleBombItemTouched(BombItem bomb) {
        bomb.eat();
//    MusicPlayer.INSTANCE.playChomp();
        updateUi();
    }

    public void checkQuestionGrid(QuestionGrid questionGrid){
        questionGrid.eat();
    }


    /**
     * This method is called when any key is pressed.
     *
     * @param event the {@link KeyEvent} happens when the key is pressed.
     */
    public void handleKeyPressed(KeyEvent event) {
        Set<Grid> obstacles = (Set<Grid>) (Set<?>) map.getPacman().getParentMap().getWalls();
        if (gameStatus == GameStatus.END) {
            return;
        }
        switch (event.getCode()) {
            case RIGHT: {
                if (!map.getPacman().isGoingToTouchGrids(Direction.RIGHT, obstacles, map.getMapConfig().getGridLength())) {
                    startGame();
                    map.getPacman().moveDown.stop();
                    map.getPacman().moveRight.stop();
                    map.getPacman().moveLeft.stop();
                    map.getPacman().moveUp.stop();
                    map.getPacman().moveRight.start();
                }
                break;
            }
            case LEFT: {
                if (!map.getPacman().isGoingToTouchGrids(Direction.LEFT, obstacles, map.getMapConfig().getGridLength())) {
                    startGame();
                    map.getPacman().moveDown.stop();
                    map.getPacman().moveRight.stop();
                    map.getPacman().moveLeft.stop();
                    map.getPacman().moveUp.stop();
                    map.getPacman().moveLeft.start();
                }
                break;
            }
            case UP: {
                if (!map.getPacman().isGoingToTouchGrids(Direction.UP, obstacles, map.getMapConfig().getGridLength())) {
                    startGame();
                    map.getPacman().moveDown.stop();
                    map.getPacman().moveRight.stop();
                    map.getPacman().moveLeft.stop();
                    map.getPacman().moveUp.stop();
                    map.getPacman().moveUp.start();
                }
                break;
            }
            case DOWN: {
                if (!map.getPacman().isGoingToTouchGrids(Direction.DOWN, obstacles, map.getMapConfig().getGridLength())) {
                    startGame();
                    map.getPacman().moveDown.stop();
                    map.getPacman().moveRight.stop();
                    map.getPacman().moveLeft.stop();
                    map.getPacman().moveUp.stop();
                    map.getPacman().moveDown.start();
                }
                break;
            }
            case SPACE: {
                if (map.getPacman().getBombCount() > 0) {

                    double gridLen = map.getMapConfig().getGridLength();
                    Timer timer = new Timer();


                    System.out.println("Pac got a bomb and hes not afraid to use it");
                    map.getPacman().setBombCount(map.getPacman().getBombCount() - 1);
//                    Set<Ghost> ghosts = map.getGhosts();
                    for (Ghost g : map.getGhosts()) {
                        if (((g.getX() <= (map.getPacman().getX() + 3 * gridLen))
                                && (g.getX() >= (map.getPacman().getX() - 3 * gridLen)))
                                && ((g.getY() <= (map.getPacman().getY() + 3 * gridLen))
                                && (g.getY() >= (map.getPacman().getY() - 3 * gridLen)))) {
                            g.setVisible(false);
                            g.freeze();
                            g.setIsAlive(false);
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    g.setIsAlive(true);
                                    g.setVisible(true);
                                    g.run();
                                }
                            }, 5 * 1000);
                        }
                    }

                } else {
                    System.out.println("no bomb attack him");
                }
                break;
            }
            case ESCAPE:
                pauseGame();

                break;
            default:
                startGame();
        }
    }

    /**
     * Wakes up all {@link Ghost}s to make them move.
     */
    private void wakeUpGhosts() {
        for (Ghost ghost : map.getGhosts()) {
            ghost.run();
        }
    }

    /**
     * Freezes all {@link Ghost}s to make them not able to move.
     */
    private void freezeGhosts() {
        for (Ghost ghost : map.getGhosts()) {
            ghost.freeze();
        }
    }

    /**
     * Sends {@link Pacman} to the {@link Spawn}.
     */
    private void sendPacmanToSpawn() {
        new Animation().shakeStage();
        Spawn spawn = map.getSpawn();
        map.getPacman().setX(spawn.getX());
        map.getPacman().setY(spawn.getY());
    }

    /**
     * Initializes the UI (title, score count, life count).
     */
    private void initUi() {
        gameController.setTitle(map.getMapConfig().getTitle());
        gameController.setScoreCount(0);
        gameController.setLifeCount(life.getRemaining(), life.getTotal());
    }

    /**
     * Updates the UI (life count, score count).
     */
    public void updateUi() {
        gameController.setLifeCount(life.getRemaining(), life.getTotal());
        gameController.setScoreCount(score.getValue());
//        gameController.setBombCount((map.getPacman().getBombCount()));
    }

    /**
     * Calculates the final {@link Score} and writes it into a corresponding file.
     */
    private void calculateScore() {
//        ScoreBoardWriter scoreBoardWriter =
//                new ScoreBoardWriter(map.getMapConfig().getTitle() + ".txt");
//        score.settle();
//        scoreBoardWriter.write(score);
    }

    /**
     * Tests if all cookies are eaten. If true, calls {@link #winGame()}.
     */
    private void checkWin(Integer currentScore) {
        if (Integer.parseInt(gameController.getScoreCount().getText())>=200) {
            winGame();
        }
        checkLevelChange(currentScore);
    }

    /**
     * adds portals to the sides of the screen.
     */
    private void addPortalsToScreen() {
        // a predicate that returns true if the wall is in the position the the portal should be in
        Predicate<Wall> isPortal = w -> ((w.getX() == 6 && w.getY() == 23) || (w.getX() == 6 && w.getY() == 0));
        map.getWalls().removeIf(isPortal);

        // add the walls to the map
        map.getPortals().add(new Portal(map, 6, 23, PortalType.A));
        map.getPortals().add(new Portal(map, 6, 0, PortalType.B));
    }


    /**
     * increases the speed of the pacman.
     */
    private void increasePacmanSpeed() {
        map.getMapConfig().setPacmanStepRate(2 * map.getMapConfig().getPacmanStepRate());
    }

    /**
     * check if the new score requires a new leve change.
     * level 2 - Adds portals to the map.
     * level 3 - Disable portals, pacman speed increases.
     * level 4 - Increase Ghosts speed.
     */
    private boolean checkLevelChange(Integer currentScore) {

        if (currentScore >= 50 && currentLevel == GameLevel.ZERO) {
            currentLevel = GameLevel.PASSED_ONE;
            SceneSwitch.INSTANCE.switchToGameLevelOne();
            gameController.setTitle("level - 2 ");
            return true;
        }

        if (currentScore >= 100 && currentLevel == GameLevel.PASSED_ONE) {
            currentLevel = GameLevel.PASSED_TWO;
            // remove portals from the screen
            SceneSwitch.INSTANCE.switchToGameLevelTwo();
            gameController.setTitle("level - 3 ");
            return true;
        }
        if (currentScore >= 150 && currentLevel == GameLevel.PASSED_TWO) {
            currentLevel = GameLevel.PASSED_THREE;
            SceneSwitch.INSTANCE.switchToGameLevelThree();
            gameController.setTitle("level - 4 ");
            return true;
        }

        return true;
    }

    /**
     * Navigates back to the Select scene, and pops up the ScoreBoard stage at the same time.
     */
    private void navigateBack() {
        // navigate back to select
//        SceneSwitch.INSTANCE.switchToSelect();

        // popup score board
    }
}
