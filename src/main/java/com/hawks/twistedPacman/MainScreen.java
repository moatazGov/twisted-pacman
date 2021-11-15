package com.hawks.twistedPacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Twisted Pacman!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}