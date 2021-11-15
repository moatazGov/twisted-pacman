package com.hawks.twistedPacman.View;

import com.hawks.twistedPacman.Controller.Controller;
import com.hawks.twistedPacman.Model.GameData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class HomeCtrl {
    private Controller controller = new Controller();
    private ArrayList<GameData> games;
    @FXML
    private Button playBtn;
    private Button adminBtn;
    private Button exitBtn;
    private Button instructionsBtn;
    private Button seeMoreBtn;
    private TableView scoresTbl = new TableView();

    public void initialize() {

        try{
            this.games = controller.getGames();
            System.out.println(this.games);
            for (GameData game : this.games) {
                TableRow row = new TableRow();
                scoresTbl.getItems().add(game);
            }
            System.out.println("Loaded data from json ");
        }catch (Exception e){
            System.out.println("error in init ");
        }

    }

    @FXML
    private void playClicked(ActionEvent event) {

    }

    @FXML
    private void adminClicked(ActionEvent event) {

    }

    @FXML
    private void exitClicked(ActionEvent event) {

    }

    @FXML
    private void instructionsClicked(ActionEvent event) {

    }

    @FXML
    private void seeMoreClicked(ActionEvent event) {

    }
}