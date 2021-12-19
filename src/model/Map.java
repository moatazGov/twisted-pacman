package model;

//import com.sun.tools.javac.util.List;
import constant.PortalType;
import javafx.scene.layout.Pane;
import util.MapConfig;
import util.MapPainter;
import util.MapReader;

import javax.sound.sampled.Port;
import java.util.Set;
import java.util.function.Predicate;

public class Map {

  /** The filename of the map file. */
  private String fileName;
  /** The nickname of the player. */
  private String nickname;
  /** The filename of the background. */
  private String backgroundFileName;
  /** The filename of the walls. */
  private String wallFileName;

//  /** The filename of the Question Grid */
//  private String questGridFileName; //TODO

  /** The configuration object of this {@link Map}. */
  private MapConfig mapConfig;

  private Set<Wall> walls;
  private Set<PacItem> pacItems;
  private Set<BombItem> bombItems;

  private Set<QuestionGrid>questionGrids; //TODO
  private Set<Question> questions; // TODO a Set to Store Questions
  /** The {@link Ghost} set in this {@link Map}. */
  private Set<Ghost> ghosts;
  /** The {@link Pacman} in this {@link Map}. */
  private Pacman pacman;
  private Spawn spawn;
  private Set<Portal> portals;

  /** Allocates a new {@link Map} object. */
  public Map() {}

  /**
   * Returns the {@link Pacman} in this {@link Map}.
   *
   * @return the {@link Pacman} in this {@link Map}
   */
  public Pacman getPacman() {
    return pacman;
  }


  /**
   */
  public Set<Wall> getWalls() {
    return walls;
  }

  /**
   */
  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  /**
   */
  public Spawn getSpawn() {
    return spawn;
  }

  public Set<PacItem> getPacItems() {
    return pacItems;
  }

  public Set<BombItem> getBombItems() {
    return bombItems;
  }

  public Set<Question> getQuestions(){ return questions;} // TODO getter for Questions

  public Set<QuestionGrid> getQuestionsGrid(){ return questionGrids;}// TODO
  /**
   * Returns the {@link Portal} set in this {@link Map}.
   *
   * @return the {@link Portal} set in this {@link Map}
   */
  public Set<Portal> getPortals() {
    return portals;
  }

  /**
   * Returns the configuration object {@link MapConfig} of this {@link Map}.
   *
   * @return the configuration object {@link MapConfig} of this {@link Map}
   */
  public MapConfig getMapConfig() {
    return mapConfig;
  }

  /**
   * Returns the nickname of the player.
   *
   * @return the nickname of the player
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Changes the nickname of the player.
   *
   * @param nickname the nickname of the player
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * Changes the filename of the map file.
   *
   * @param fileName the filename of the map file
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Returns the filename of the background.
   *
   * @return the filename of the background
   */
  public String getBackgroundFileName() {
    return backgroundFileName;
  }

  /**
   * Changes the filename of the background.
   *
   * @param backgroundFileName the filename of the background
   */
  public void setBackgroundFileName(String backgroundFileName) {
    this.backgroundFileName = backgroundFileName;
  }

  /**
   * Returns the filename of the walls.
   *
   * @return the filename of the walls
   */
  public String getWallFileName() {
    return wallFileName;
  }



  /**
   * Changes the filename of the walls.
   *
   * @param wallFileName the filename of the walls
   */
  public void setWallFileName(String wallFileName) {
    this.wallFileName = wallFileName;
  }

//  //TODO what the fuck did i do here
//  public String getQuestionGridFileName(){
//    return questGridFileName;
//  }
//  public void setQuestionGridFileName(String questGridFileName){
//    this.questGridFileName=questGridFileName;
//  }
  /**
   * Reads the map file of {@link #fileName}.
   *
   * <p>This method uses {@link MapReader} to get results.
   */
  private void read() {

    // read map
    MapReader mapReader = new MapReader(fileName, this);

    // get config
    mapReader.readFileForConfig();
    mapConfig = mapReader.getMapConfig();

    // get grids
    mapReader.readFileForMap();
    walls = mapReader.getWalls();
    pacItems = mapReader.getPacItems();
    bombItems = mapReader.getBombItems();
    pacman = mapReader.getPacman();
    ghosts = mapReader.getGhosts();
    spawn = mapReader.getSpawn();
    portals = mapReader.getPortals();
    questions=mapReader.getQuestions();// TODO
    questionGrids=mapReader.getQuestionsGrids();
  }

  /**
   * Draws the map to the given {@link Pane} node.
   *
   * <p>This method calls {@link #read()} to get result first, then uses {@link MapPainter} to paint
   * the map.
   *
   * @param root a {@link Pane} that is expected to be drawn with the map on it
   */
  public void draw(Pane root) {

    // read map
    read();

    // paint map
    MapPainter mapPainter = new MapPainter(root);
    mapPainter.drawWalls(walls);
    mapPainter.drawCookies(pacItems);
    mapPainter.drawBombs(bombItems);
    mapPainter.drawPacman(pacman);
    mapPainter.drawGhost(ghosts);
    mapPainter.drawQuestion(questions); //TODO
    mapPainter.drawQuestionGrid(questionGrids); //TODO
  }

  /**
   * adds portals to the sides of the screen.
   */
  public void addPortalsToScreen(Pane root){
    try{// a predicate that returns true if the wall is in the position the the portal should be in
      Predicate<Wall> isPortal = w -> ((w.getX() == 24 * mapConfig.getGridLength() && w.getY() == 6 * mapConfig.getGridLength()) || (w.getX() == 0 && w.getY() == 6 * mapConfig.getGridLength()));
      walls.removeIf(isPortal);

      // add the portals to the map
      Portal portal_1 = new Portal(this, 24, 6, PortalType.A);
      Portal portal_2 = new Portal(this, 0, 6, PortalType.B);
//    Portal portal_3 = new Portal(this, 3, 6, PortalType.B);
      portal_2.setTwinPortal(portal_1);
      portal_1.setTwinPortal(portal_2);
//    portal_3.setTwinPortal(portal_2);
      portals.add(portal_1);
      portals.add(portal_2);
      MapPainter mapPainter = new MapPainter(root);
//      mapPainter.drawWalls(walls);
      mapPainter.drawPortals(portals);
    }catch (Exception e){
      System.out.println("error");
    }

  }

  /**
   * increases the speed of the pacman.
   */
  private void increasePacmanSpeed(){
    this.getMapConfig().setPacmanStepRate(2 * this.getMapConfig().getPacmanStepRate());
  }


}
