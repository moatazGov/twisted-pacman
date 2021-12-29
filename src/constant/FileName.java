package constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * object to store constant file names.
 */
public class FileName {
  public static final String MAP = "resources/map/map1.txt";

  public static final String IMAGE_MED_QUESTION = "/resources/image/med_ques.png"; //Todo
  public static final String IMAGE_EASY_QUESTION = "/resources/image/easy_ques.png"; //Todo
  public static final String IMAGE_HARD_QUESTION = "/resources/image/hard_ques.png"; //Todo

  public static final String IMAGE_PACMAN = "/resources/image/pacman.png";
  public static final String IMAGE_BIG = "/resources/image/cookie/cookie10.png";
  public static final String IMAGE_BOMB = "/resources/images/bomb.png";
  public static final String IMAGE_ANGRY_PAC = "/resources/image/angryPac.png";

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

  public static final String VIEW_HOME = "/resources/fxml/home-view.fxml";
  public static final String VIEW_GAME = "/resources/view/game.fxml";
  public static final String VIEW_SELECT = "/resources/view/select.fxml";
}
