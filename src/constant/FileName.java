package constant;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 *
 * <h1>FileName</h1>
 *
 * <p>A {@link FileName} is an object to store constant file names.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class FileName {
  public static final Set<String> MAPS =
      new TreeSet<>(
          Arrays.asList(
              "resources/map/map1.txt",
              "resources/map/map2.txt",
              "resources/map/map3.txt",
              "resources/map/map4.txt",
              "resources/map/#005 Less is More.txt",
              "resources/map/#006 Up and Down.txt",
              "resources/map/#007 One Way.txt",
              "resources/map/#008 The Maze.txt",
              "resources/map/#009 Accel World.txt",
              "resources/map/#010 Spires.txt"));

  public static final Set<String> IMAGE_BACKGROUNDS =
      new TreeSet<>(
          Arrays.asList(
              "/resources/image/floor/bedrock.png",
              "/resources/image/floor/dirt.png",
              "/resources/image/floor/gravel.png",
              "/resources/image/floor/packed_ice.png",
              "/resources/image/floor/polished_andesite.png",
              "/resources/image/floor/polished_diorite.png",
              "/resources/image/floor/polished_granite.png",
              "/resources/image/floor/red_concrete.png",
              "/resources/image/floor/red_sand.png",
              "/resources/image/floor/red_terracotta.png",
              "/resources/image/floor/sandstone.png",
              "/resources/image/floor/stone.png"));
  public static final Set<String> IMAGE_OBSTACLES =
      new TreeSet<>(
          Arrays.asList(
              "/resources/image/obstacle/prismarine_bricks.png",
              "/resources/image/obstacle/red_nether_bricks.png",
              "/resources/image/obstacle/stone_bricks.png"));
  public static final String IMAGE_QUESTION = "/resources/image/med_ques.png";
  public static final String IMAGE_PACMAN = "/resources/image/pacman.png";
  public static final String IMAGE_COOKIE_SMALL = "/resources/image/cookie/cookie1.png";
  public static final String IMAGE_COOKIE_MEDIUM = "/resources/image/cookie/cookie5.png";
  public static final String IMAGE_COOKIE_BIG = "/resources/image/cookie/cookie10.png";
  public static final String IMAGE_BOMB = "/resources/images/bomb.png";
  public static final String IMAGE_ANGRYPAC = "/resources/image/angryPac.png";

  public static final Set<String> IMAGE_GHOSTS =
      new HashSet<>(
          Arrays.asList(
              "/resources/image/ghost/ghost1.png",
              "/resources/image/ghost/ghost2.png",
              "/resources/image/ghost/ghost3.png",
              "/resources/image/ghost/ghost4.png",
              "/resources/image/ghost/ghost5.png",
              "/resources/image/ghost/ghost6.png",
              "/resources/image/ghost/ghost7.png",
              "/resources/image/ghost/ghost8.png",
              "/resources/image/ghost/ghost9.png",
              "/resources/image/ghost/ghost10.png"));

  public static final String IMAGE_PORTAL_A = "/resources/image/portal/portal1.png";
  public static final String IMAGE_PORTAL_B = "/resources/image/portal/portal2.png";

  public static final String VIEW_HOME = "/fxml/home-view.fxml";
  public static final String VIEW_GAME = "/resources/view/game.fxml";
  public static final String VIEW_SELECT = "/resources/view/select.fxml";
  public static final String VIEW_SCOREBOARD = "/resources/view/scoreboard.fxml";

  public static final String MUSIC_BEGINNING = "resources/music/pacman_beginning.wav";
  public static final String MUSIC_CHOMP = "resources/music/pacman_chomp.wav";
  public static final String MUSIC_DEATH = "resources/music/pacman_death.wav";
  public static final String MUSIC_EATTING = "resources/music/pacman_eatfruit.wav";
  public static final String MUSIC_SETUP = "resources/music/pacman_intermission.wav";

  private static final String SYSTEM_PATH =
      System.getProperty("user.home") + File.separator + ".pacman" + File.separator;

  public static final String SCORE_BOARD_PATH = SYSTEM_PATH + "scoreboard/";
}
