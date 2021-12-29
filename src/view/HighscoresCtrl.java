package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.GameData;
import model.SysData;

import java.io.IOException;
import java.util.ArrayList;

import static constant.FileName.VIEW_HOME;

public class HighscoresCtrl {

    Parent root;
    Stage stage;
    Scene scene;

    private ObservableList<GameData> games = FXCollections.observableArrayList();

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
        gamesData.sort((x,y) -> x.getScore() > y.getScore() ? -1 : x.getScore() < y.getScore() ? 1 : 0);
        games.addAll(gamesData);
        scoreCol.setCellValueFactory(new PropertyValueFactory<GameData, Integer>("score"));
        nameCol.setCellValueFactory(new PropertyValueFactory<GameData, String>("nickName"));
        gamesTbl.setItems(games);
    }
    @FXML
    private void backClicked(ActionEvent event) throws IOException {
        setTableappearance();
        createTable();
        root = FXMLLoader.load(getClass().getResource(VIEW_HOME));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
}
