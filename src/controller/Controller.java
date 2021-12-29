package controller;

import constant.Direction;
import constant.GameLevel;
import constant.GameStatus;
import javafx.scene.input.KeyEvent;
import model.*;
import util.Animation;
import util.SceneSwitch;
import view.GameCtrl;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <h1>Controller</h1>
 * <p>
 * object of utility to manage the game status and process globally,
 * and reflect the realtime results in the UI.
 */
public enum Controller {
    /**
     * The shared instance for global use.
     */
    INSTANCE;


    /**
     * The current playing {@link Map}.
     */
    private Map map;


    /**
     * the index off the current easy question.
     */
    private int currentEasyIndex = 1;
    /**
     * the index off the current medium question.
     */
    private int currentMedIndex = 1;
    /**
     * the index off the current hard question.
     */
    private int currentHardIndex = 1;
    /**
     * the current level of the game.
     */
    private GameLevel currentLevel = GameLevel.ZERO;

    /**
     * a class the holds all the system data (questions and games history )
     */
    private SysData sysData = new SysData();

    /**
     * The current {@link GameCtrl}.
     */
    private GameCtrl gameCtrl;

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


    /**
     * get the current score of the player.
     *
     * @return
     */
    public Score getScore() {
        return score;
    }

    /**
     * set the current score of the player.
     *
     * @param score
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * increases the value of the score with the given newScore
     *
     * @param newScore
     */
    public void incScore(int newScore) {
        this.score.gain(newScore);
    }

    /**
     * decreases the value o the score with the given newScore
     *
     * @param newScore
     */
    public void decScore(int newScore) {
        this.score.gain(newScore);
    }

    /**
     * Initializes the game properties based on the given {@link Map} and updates UI via {@link
     * GameCtrl}.
     *
     * @param map      the current {@link Map}
     * @param gameCtrl the {@link GameCtrl} linked to the current displayed UI
     */
    public void init(Map map, GameCtrl gameCtrl) {
        // add map
        this.map = map;

        // add game controller
        this.gameCtrl = gameCtrl;

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
     * Pauses the game moving objects, and open paused game popup.
     */
    public void pauseGame() {
        if (gameStatus == GameStatus.PAUSE) {
            freezeGhosts();
            map.getPacman().freeze();
            SceneSwitch.INSTANCE.switchToPause();
            gameStatus = GameStatus.START;

        }
    }

    /**
     * Resumes the state of the game.
     */
    public void continueGame() {
        if (gameStatus == GameStatus.CONTINUE) {

        }
    }

    /**
     * Pauses the game moving objects, without opening paused game popup.
     */
    public void pauseGameNoPopUp() {
        if (gameStatus == GameStatus.PAUSE) {
            SceneSwitch.INSTANCE.returnToGame();
        }

        freezeGhosts();
        map.getPacman().freeze();
        gameStatus = GameStatus.PAUSE;

    }

    /**
     * Loses the game.
     */
    public void loseGame() {
        if (getGameStatus() == GameStatus.START) {
            endGame(map.getNickname(), "LOST", String.valueOf(score.getValue()));
        }
    }

    /**
     * Wins the game.
     * <p>
     * to the Select scene.s
     */
    public void winGame() {
        if (getGameStatus() == GameStatus.START) {
            endGame(map.getNickname(), "WON", String.valueOf(this.score.getValue()));
        }
    }

    /**
     * store the new score to json files.
     *
     * @param name
     * @param status
     * @param score
     * @return
     */
    private boolean storeScore(String name, String status, String score) {
        try {
            sysData.load();
            sysData.getGames().add(new GameData(name, Integer.valueOf(score)));
            sysData.save();
            return true;
        } catch (Exception e) {
            System.out.println("caught error in store score");
            return false;
        }
    }


    /**
     * Ends the game, and switches to the correct end-game view
     */
    public void endGame(String name, String status, String score) {
        freezeGhosts();
        storeScore(name, status, score);
        SceneSwitch.INSTANCE.switchToEndGame(name, status, score);
        gameStatus = GameStatus.END;
    }

    /**
     * This method is called when any {@link Ghost} is touching the {@link Pacman}.
     *
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
    private boolean showQuestionPopUp(Question question) {
        return true;
    }

    public void handleBombItemTouched(BombItem bomb) {
        bomb.eat();
        updateUi();
    }

    /**
     * generate a new question at a random grid, chooses a pacItem randomly from the map and replaces it with a new question.
     */
    private boolean generateRandomQuestionGrid(QuestionGrid questionGrid) {
        int index = 0;
        int wantedIndex = getRandomNumberUsingNextInt(0, map.getPacItems().size());
        // find random pacItem to replace and set visible value to false;
        PacItem randPacItem = null;
        for (PacItem pacItem : map.getPacItems()) {
            if (index == wantedIndex) {
                randPacItem = pacItem;
            }
            index++;
        }
        if (randPacItem != null) {
            randPacItem.setVisible(false);
            questionGrid.setVisible(false);
            Double itemXTmp = randPacItem.getX();
            Double itemYTmp = randPacItem.getY();

            // set the question of the questionGrid according to the level of the eaten questionGrid
            if (questionGrid.getQuestion() instanceof EasyQuestion) {
                questionGrid.setQuestion(SysData.getInstance().getEasyQuestions().get(currentEasyIndex % SysData.getInstance().getEasyQuestions().size()));
                currentEasyIndex++;
            }
            if (questionGrid.getQuestion() instanceof MediumQuestion) {
                questionGrid.setQuestion(SysData.getInstance().getMedQuestions().get(currentMedIndex % SysData.getInstance().getMedQuestions().size()));
                currentMedIndex++;
            }
            if (questionGrid.getQuestion() instanceof HardQuestion) {
                questionGrid.setQuestion(SysData.getInstance().getHardQuestions().get(currentHardIndex % SysData.getInstance().getHardQuestions().size()));
                currentHardIndex++;
            }

            //set the position of the new pacitem
            randPacItem.setX(questionGrid.getX());
            randPacItem.setY(questionGrid.getY());


            PacItem finalRandPacItem = randPacItem;
            questionGrid.setX(itemXTmp);
            questionGrid.setY(itemYTmp);
            finalRandPacItem.setVisible(true);
            questionGrid.setVisible(true);
            return true;
        }
        return false;
    }

    /**
     * Generate random number in the given range.
     *
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
     *
     * @param questionGrid
     */
    public void handleQuestionGrid(QuestionGrid questionGrid) {
        pauseGameNoPopUp();
        generateRandomQuestionGrid(questionGrid);
        SceneSwitch.INSTANCE.switchToQuestion(questionGrid);
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
                if (this.gameStatus == GameStatus.START) {
                    this.gameStatus = GameStatus.PAUSE;
                    pauseGame();
                } else if (this.gameStatus == GameStatus.PAUSE) {
                    this.gameStatus = GameStatus.CONTINUE;
                    continueGame();
                }

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
        gameCtrl.setTitle(map.getMapConfig().getTitle());
        gameCtrl.setScoreCount(0);
        gameCtrl.setLifeCount(life.getRemaining(), life.getTotal());
    }

    /**
     * Updates the UI (life count, score count).
     */
    public void updateUi() {
        gameCtrl.setLifeCount(life.getRemaining(), life.getTotal());
        gameCtrl.setScoreCount(score.getValue());
    }


    /**
     * Tests if all cookies are eaten. If true, calls {@link #winGame()}.
     */
    private void checkWin(Integer currentScore) {
        if (Integer.parseInt(gameCtrl.getScoreCount().getText()) >= 200) {
            winGame();
        }
        checkLevelChange(currentScore);
    }

    /**
     * check if the new score requires a new leve change.
     * level 2 - Adds portals to the map.
     * level 3 - Disable portals, pacman speed increases.
     * level 4 - Increase Ghosts speed.
     */
    private boolean checkLevelChange(Integer currentScore) {

        if (currentScore >= 51 && currentLevel == GameLevel.ZERO) {
            currentLevel = GameLevel.PASSED_ONE;
            SceneSwitch.INSTANCE.switchToGameLevelOne();
            gameCtrl.setTitle("level - 2 ");
            return true;
        }

        if (currentScore >= 101 && currentLevel == GameLevel.PASSED_ONE) {
            currentLevel = GameLevel.PASSED_TWO;
            SceneSwitch.INSTANCE.switchToGameLevelTwo();
            gameCtrl.setTitle("level - 3 ");
            return true;
        }

        if (currentScore >= 151 && currentLevel == GameLevel.PASSED_TWO) {
            currentLevel = GameLevel.PASSED_THREE;
            SceneSwitch.INSTANCE.switchToGameLevelThree();
            gameCtrl.setTitle("level - 4 ");
            return true;
        }

        return true;
    }

}
