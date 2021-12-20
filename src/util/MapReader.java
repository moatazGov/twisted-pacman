package util;

import model.Portal;
import constant.MapResolution;
import constant.PortalType;
import model.*;
import view.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.lang.Math;
import model.PacItem;
import controller.FactoryQuestionGrid;

public class MapReader {
  int index = 0;

  /** The filename of the map file. */
  private String fileName;

  /** The line count of the map. */
  private int lineCount;

  /** The grid count of the map. */
  private int gridCount;

  /** The map read from the map file. */
  private Map map;

  /** The map configuration. */
  private MapConfig mapConfig;

  /** The {@link Wall} set in the map. */
  private Set<Wall> walls;

  /** The {@link PacItem} set in the map. */
  private Set<PacItem> pacItems;

  /** The {@link BombItem} set in the map. */
  private Set<BombItem> bombItems;

  /** The {@link Ghost} set in the map. */
  private Set<Ghost> ghosts;

  private Set<Question>questions;

  private Set<QuestionGrid>questionsGrids;

  /** The {@link Pacman} in the map. */
  private Pacman pacman;

  /** The {@link Spawn} in the map. */
  private Spawn spawn;

  /** The {@link Portal} A in the map. */
  private Portal portalA;

  /** The {@link Portal} B in the map. */
  private Portal portalB;

  /**
   * Allocates a new {@link MapReader} object.
   *
   * @param fileName the filename of the map file to be read
   * @param map the map object itself to be injected
   */
  public MapReader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.lineCount = 0;
    this.gridCount = 0;
    this.walls = new HashSet<>();
    this.pacItems = new HashSet<PacItem>();
    this.bombItems = new HashSet<BombItem>();
    this.ghosts = new HashSet<>();
    this.mapConfig = new MapConfig(50);
    this.questions=new HashSet<Question>();
    this.questionsGrids=new HashSet<QuestionGrid>();
    // set title
    String title = fileName.substring(fileName.lastIndexOf("/") + 1); // remove path
    title = title.substring(0, title.lastIndexOf(".")); // remove type suffix
    mapConfig.setTitle(title);
  }

  /**
   * Returns the {@link Wall} set in this {@link Map}.
   *
   * @return the {@link Wall} set in this {@link Map}
   */
  public Set<Wall> getWalls() {
    return walls;
  }

  /**
   * Returns the {@link Pacman} in this {@link Map}.
   *
   * @return the {@link Pacman} in this {@link Map}
   */
  public Pacman getPacman() {
    return pacman;
  }

  /**
   * Returns the {@link Ghost} set in this {@link Map}.
   *
   * @return the {@link Ghost} set in this {@link Map}
   */
  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  public Set<PacItem> getPacItems() {
    return pacItems;
  }

  public Set<BombItem> getBombItems() {
    return bombItems;
  }

  public Set<QuestionGrid> getQuestionsGrids(){return questionsGrids;}

  public Set<Question> getQuestions(){ return questions;}
  /**
   * Returns the {@link Spawn} in this {@link Map}.
   *
   * @return the {@link Spawn} in this {@link Map}
   */
  public Spawn getSpawn() {
    return spawn;
  }

  /**
   * Returns the {@link Portal} set in this {@link Map}.
   *
   * @return the {@link Portal} set in this {@link Map}
   */
  public Set<Portal> getPortals() {
    Set<Portal> portals = new HashSet<>();
    if (portalA != null) {
      portals.add(portalA);
    }
    if (portalB != null) {
      portals.add(portalB);
    }

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
   * Returns the score value based on a grid string letter.
   *
   * <p>{@code .} - a cookie with score value {@code 1}; {@code o} - a cookie with score value
   * {@code 5}; {@code O} - a cookie with score value {@code 10}. (Default case: {@code 0}.)
   *
   * @param cookieGrid a string letter representing a grid
   * @return the score value of a {@link Wall}
   */
  private int getPacItemScore(String cookieGrid) {
    switch (cookieGrid) {
      case ".":
        return 1;
      case "o":
        return 5;
      case "O":
        return 10;
      default:
        return 0;
    }
  }

  /**
   * Tests if a given string letter represents a {@link Pacman}.
   *
   * <p>{@code @} - a {@link Pacman}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Pacman}; {@code false}
   *     otherwise
   */
  private boolean isPacmanGrid(String grid) {
    return grid.equals("@");
  }

  /**
   * Tests if a given string letter represents a {@link Ghost}.
   *
   * <p>{@code @} - a {@link Ghost}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Ghost}; {@code false}
   *     otherwise
   */
  private boolean isGhostGrid(String grid) {
    return grid.equals("X");
  }


  /**
   * Tests if a given string letter represents a {@link Wall}.
   *
   * <p>{@code #} - a {@link Wall}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Wall}; {@code false}
   *     otherwise
   */
  private boolean isObstacleGrid(String grid) {
    return grid.equals("#");
  }

  /**
   * Tests if a given string letter represents a {@link PacItem}.
   *
   * <p>{@code #} - a {@link PacItem}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link PacItem}; {@code false}
   *     otherwise
   */
  private boolean isPacItemGrid(String grid) {
    return grid.equals(".");
  }

  /**
   * Tests if a given string letter represents a {@link Question}.
   *
   * <p>{@code #} - a {@link Question}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Question}; {@code false}
   *     otherwise
   */
  private boolean isQuestionGrid(String grid) {
    return grid.equals("?");
  }

  /**
   * Tests if a given string letter represents a {@link BombItem}.
   *
   * <p>{@code $} - a {@link BombItem}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link BombItem}; {@code false}
   *     otherwise
   */
  private boolean isBombGrid(String grid) {
    return grid.equals("$");
  }

  /**
   * Tests if a given string letter represents a {@link Portal} A.
   *
   * <p>{@code <} - a {@link Portal} A.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Portal} A; {@code false}
   *     otherwise
   */
  private boolean isPortalAGrid(String grid) {
    return grid.equals("<");
  }

  /**
   * Tests if a given string letter represents a {@link Portal} B.
   *
   * <p>{@code >} - a {@link Portal} B.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Portal} B; {@code false}
   *     otherwise
   */
  private boolean isPortalBGrid(String grid) {
    return grid.equals(">");
  }

  /**
   * Tests if a given string line represents a comment line.
   *
   * <p>A line starting with {@code \/*} and ending with {@code *\/} is recognised to be a comment
   * line.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a comment line; {@code false} otherwise
   */
  private boolean isCommentLine(String line) {
    return line.startsWith("/*") && line.endsWith("*/");
  }

  /**
   * Tests if a given string line represents an empty line.
   *
   * <p>A line consisting of only spaces or nothing is recognised to be an empty line.
   *
   * @param line a line string
   * @return {@code true} if the given string represents an empty line; {@code false} otherwise
   */
  private boolean isEmptyLine(String line) {
    return line.replaceAll("\\s+", "").isEmpty();
  }

  /**
   * Tests if a given string line represents a configuration line for pacman step rate.
   *
   * <p>A line starting with {@code @PACMAN_STEP_RATE } is recognised to be a configuration line for
   * pacman step rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a a configuration line for pacman step
   *     rate; {@code false} otherwise
   */
  private boolean isPacmanStepRate(String line) {
    return line.startsWith("@PACMAN_STEP_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for ghost step rate.
   *
   * <p>A line starting with {@code @GHOST_STEP_RATE } is recognised to be a configuration line for
   * ghost step rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for ghost step rate;
   *     {@code false} otherwise
   */
  private boolean isGhostStepRate(String line) {
    return line.startsWith("@GHOST_STEP_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for cookie padding rate.
   *
   * <p>A line starting with {@code @COOKIE_PADDING_RATE } is recognised to be a configuration line
   * for cookie padding rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for cookie padding
   *     rate; {@code false} otherwise
   */
  private boolean isCookiePaddingRate(String line) {
    return line.startsWith("@COOKIE_PADDING_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for ghost padding rate.
   *
   * <p>A line starting with {@code @GHOST_PADDING_RATE } is recognised to be a configuration line
   * for ghost padding rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for ghost padding
   *     rate; {@code false} otherwise
   */
  private boolean isGhostPaddingRate(String line) {
    return line.startsWith("@GHOST_PADDING_RATE ");
  }

  /**
   * Analyses a given line for configurations.
   *
   * @param line a line string
   */
  private void processConfigLine(String line) {

    // check if the line is a comment or is empty
    if (isCommentLine(line) || isEmptyLine(line)) {
      return;
    }

    // pacman step rate
    if (isPacmanStepRate(line)) {
      double pacmanStepRate = Double.parseDouble(line.replace("@PACMAN_STEP_RATE ", "").trim());
      mapConfig.setPacmanStepRate(pacmanStepRate);
      return;
    }

    // ghost step rate
    if (isGhostStepRate(line)) {
      double ghostStepRate = Double.parseDouble(line.replace("@GHOST_STEP_RATE ", "").trim());
      mapConfig.setGhostStepRate(ghostStepRate);
      return;
    }

    // cookie padding rate
    if (isCookiePaddingRate(line)) {
      double cookiePaddingRate =
          Double.parseDouble(line.replace("@COOKIE_PADDING_RATE ", "").trim());
      mapConfig.setCookiePaddingRate(cookiePaddingRate);
      return;
    }

    // ghost padding rate
    if (isGhostPaddingRate((line))) {
      double ghostPaddingRate = Double.parseDouble(line.replace("@GHOST_PADDING_RATE ", "").trim());
      mapConfig.setGhostPaddingRate(ghostPaddingRate);
      return;
    }

    // grid length (in map lines)
    mapConfig.setGridLength(MapResolution.WIDTH / line.length());
  }

  /**
   * Analyses a given line for map.
   *
   * @param line a line string
   */
  private void processMapLine(String line) {
    // skip all not map lines
    if (isCommentLine(line)
        || isEmptyLine(line)
        || isPacmanStepRate(line)
        || isGhostStepRate(line)
        || isCookiePaddingRate(line)
        || isGhostPaddingRate(line)) {
      return;
    }

    // read every character in the line
    String[] grids = line.split("");
    for (String grid : grids) {

      // obstacle
      if (isObstacleGrid(grid)) {
        Wall obstacle = new Wall(map, gridCount, lineCount);
        walls.add(obstacle);
      }

      // pacman
      if (isPacmanGrid(grid)) {
        pacman = new Pacman(map, gridCount, lineCount);
        spawn = new Spawn(map, gridCount, lineCount);
      }

      // pacitem
      if (isPacItemGrid(grid)) { //TODO
        PacItem pacItem = new PacItem(map, gridCount, lineCount, getPacItemScore(grid));
        pacItems.add(pacItem);
       // ArrayList <PacItem> itemList= ArrayList <PacItem> ();

      }
      // bombitem
      if (isBombGrid(grid)) {
        BombItem bombItem = new BombItem(map, gridCount, lineCount);
        bombItems.add(bombItem);
      }

        //question TODO
        if(isQuestionGrid(grid)){

            ArrayList<Question> questions = SysData.getInstance().getQuestions(); // getting the JSON arraylist from SysData
            Question currentQuestion = questions.get(index%questions.size());
            index++;
            FactoryQuestionGrid factoryQuestionGrid = new FactoryQuestionGrid();
            QuestionGrid questionGrid = factoryQuestionGrid.getQuestionGrid(map,gridCount, lineCount, currentQuestion);
            this.questions.add(currentQuestion);
          getQuestionsGrids().add(questionGrid); //TODO
        }
      // ghost
      if (isGhostGrid(grid)) {
        Ghost ghost = new Ghost(map, gridCount, lineCount);
        ghosts.add(ghost);
      }

      // portalA
      if (isPortalAGrid(grid)) {
        portalA = new Portal(map, gridCount, lineCount, PortalType.A);
      }

      // portalB
      if (isPortalBGrid(grid)) {
        portalB = new Portal(map, gridCount, lineCount, PortalType.B);
      }

      gridCount++;
    }
    lineCount++;
    gridCount = 0;
  }

  /**
   * Reads a map file and processes for the configurations or the map.
   *
   * @param isForConfig {@code true} this calling for reading is for configurations; {@code false}
   *     if for the map
   */
  private void readFile(boolean isForConfig) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = Main.class.getResource(fileName);
    String path = null;
    try {
      path = Objects.requireNonNull(resource).toURI().getPath();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (isForConfig) {
          processConfigLine(line);
        } else {
          processMapLine(line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Reads the file for the configurations. */
  public void readFileForConfig() {
    readFile(true);
    mapConfig.calculate();
  }

  /** Reads the file for the map. */
  public void readFileForMap() {
    readFile(false);

    // link two portals
    if (portalA != null && portalB != null) {
      portalA.setTwinPortal(portalB);
      portalB.setTwinPortal(portalA);
    }
  }
}
