package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.GameData;

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

    Parent root;
    Scene scene;
    Stage stage;

    public void initialize() {

        try {
            this.games = controller.getGames();
            for (GameData game : this.games) {
                TableRow row = new TableRow();
                scoresTbl.getItems().add(game);
            }
            System.out.println("Loaded data from json ");
        } catch (Exception e) {
            System.out.println("error in init ");
        }

    }

    @FXML
    private void playClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/nickname.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        Popup pop = PopupBuilder.create().content(root).width(50).height(100).autoFix(true).build();
//        pop.show(stage);
//        pop.setOnCloseRequest(event1 -> event1.);

    }

    @FXML
    private void adminClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/admin-login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



//        Popup popup = new Popup();
//        NicknameCtrl controller = new NicknameCtrl();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/questionsAdmin.fxml"));
//        loader.setController(controller);
//        popup.getContent().add((Node) loader.load());
    }

    @FXML
    private void exitClicked(ActionEvent event) {

    }

    @FXML
    private void instructionsClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/instructions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void seeMoreClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/highscores.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}