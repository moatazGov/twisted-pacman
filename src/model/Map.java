package model;

import constant.PortalType;
import javafx.scene.layout.Pane;
import util.MapConfig;
import util.MapPainter;
import util.MapReader;

import java.util.Set;
import java.util.function.Predicate;

public class Map {

    /**
     * The filename of the map file.
     */
    private String fileName;
    /**
     * The nickname of the player.
     */
    private String nickname;
    /**
     * The filename of the background.
     */
    private String backgroundFileName;
    /**
     * The filename of the walls.
     */
    private String wallFileName;
    /**
     * The configuration object of this {@link Map}.
     */
    private MapConfig mapConfig;
    /**
     * a set of the walls objects of the game
     **/
    private Set<Wall> walls;
    /**
     * a set of the pacItems objects of the game
     **/
    private Set<PacItem> pacItems;
    /**
     * a set of the bombItems objects of the game
     **/
    private Set<BombItem> bombItems;
    /**
     * a set of the questionsGrid objects of the game
     **/
    private Set<QuestionGrid> questionGrids;
    /**
     * The {@link Ghost} set in this {@link Map}.
     */
    private Set<Ghost> ghosts;
    /**
     * The {@link Pacman} in this {@link Map}.
     */
    private Pacman pacman;
    /**
     * the spawn grid pacman spawns to
     **/
    private Spawn spawn;
    /**
     * a Set containing the portals of the map
     **/
    private Set<Portal> portals;

    /**
     * Allocates a new {@link Map} object.
     */
    public Map() {
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
     * getter for the field walls
     *
     * @return
     */
    public Set<Wall> getWalls() {
        return walls;
    }

    /**
     * getter for the field ghosts
     *
     * @return
     */
    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     * getter for the field spawn
     *
     * @return
     */
    public Spawn getSpawn() {
        return spawn;
    }

    /**
     * getter for the field pacItems
     *
     * @return
     */
    public Set<PacItem> getPacItems() {
        return pacItems;
    }

    /**
     * getter for the field bombItems
     *
     * @return
     */
    public Set<BombItem> getBombItems() {
        return bombItems;
    }

    /**
     * getter for the field questionGrids
     *
     * @return
     */
    public Set<QuestionGrid> getQuestionsGrid() {
        return questionGrids;
    }// TODO

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

        // get all the grids of the map
        mapReader.readFileForMap();
        walls = mapReader.getWalls();
        pacItems = mapReader.getPacItems();
        bombItems = mapReader.getBombItems();
        pacman = mapReader.getPacman();
        ghosts = mapReader.getGhosts();
        spawn = mapReader.getSpawn();
        portals = mapReader.getPortals();
        questionGrids = mapReader.getQuestionsGrids();
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

        // paint all the map elements.
        MapPainter mapPainter = new MapPainter(root);
        mapPainter.drawWalls(walls);
        mapPainter.drawQuestionGrid(questionGrids);
        mapPainter.drawCookies(pacItems);
        mapPainter.drawBombs(bombItems);
        mapPainter.drawPacman(pacman);
        mapPainter.drawGhost(ghosts);
    }

    /**
     * adds portals to the sides of the screen.
     */
    public void addPortalsToScreen(Pane root) {
        try {// a predicate that returns true if the wall is in the position the the portal should be in
            Predicate<Wall> isPortal = w -> ((w.getX() == 24 * mapConfig.getGridLength() && w.getY() == 6 * mapConfig.getGridLength()) || (w.getX() == 0 && w.getY() == 6 * mapConfig.getGridLength()));
            walls.removeIf(isPortal);

            // add the portals to the map
            Portal portal_1 = new Portal(this, 24, 6, PortalType.A);
            Portal portal_2 = new Portal(this, 0, 6, PortalType.B);
            portal_2.setTwinPortal(portal_1);
            portal_1.setTwinPortal(portal_2);
            portals.add(portal_1);
            portals.add(portal_2);
            MapPainter mapPainter = new MapPainter(root);
            mapPainter.drawPortals(portals);
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    /**
     * Removes the portals from the screen
     *
     * @param root
     */
    public void removePortalsToScreen(Pane root) {
        try {
            portals.forEach(x -> x.setVisible(false));
            portals.forEach(x -> x.close());
            MapPainter mapPainter = new MapPainter(root);
            mapPainter.drawPortals(portals);
        } catch (Exception e) {
            System.out.println("error");
        }

    }

}
