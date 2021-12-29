package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.GameData;
import model.SysData;
import util.SceneSwitch;

import java.io.IOException;
import java.util.ArrayList;

public class HomeCtrl {

    Parent root;
    Scene scene;
    Stage stage;
    private ObservableList<GameData> games = FXCollections.observableArrayList();
    @FXML
    private Button playBtn;
    @FXML
    private Button adminBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button instructionsBtn;
    @FXML
    private Button seeMoreBtn;
    @FXML
    private TableView<GameData> gamesTbl = new TableView<GameData>();
    @FXML
    private TableColumn<GameData, String> nameCol;
    @FXML
    private TableColumn<GameData, Integer> scoreCol;

    private void setTableappearance() {
        gamesTbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void createTable() {
        SysData.getInstance().load();
        SysData sysData = SysData.getInstance();
        ArrayList<GameData> gamesData = sysData.getGames();
        gamesData.sort((x, y) -> x.getScore() > y.getScore() ? -1 : x.getScore() < y.getScore() ? 1 : 0);
        ArrayList<GameData> wanted = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            wanted.add(gamesData.get(i));
        }
        games.addAll(wanted);
        scoreCol.setCellValueFactory(new PropertyValueFactory<GameData, Integer>("score"));
        nameCol.setCellValueFactory(new PropertyValueFactory<GameData, String>("nickName"));
        gamesTbl.setItems(games);

    }

    public void initialize() {

        try {
            setTableappearance();
            createTable();
            System.out.println("Loaded data from json ");
        } catch (Exception e) {
            System.out.println("error in init ");
        }

    }

    @FXML
    private void playClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/nickname.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void adminClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/admin-login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        SceneSwitch.INSTANCE.exitApplication();
    }

    @FXML
    private void instructionsClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/instructions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void seeMoreClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/highscores.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}