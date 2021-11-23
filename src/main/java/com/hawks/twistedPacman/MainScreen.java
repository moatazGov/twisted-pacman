package com.hawks.twistedPacman;

import com.hawks.twistedPacman.Model.SysData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("/fxml/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Twisted Pacman!");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            System.out.println("error");
        }    }

    public static void main(String[] args) {
        launch();
    }
}