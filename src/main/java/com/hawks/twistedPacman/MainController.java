package com.hawks.twistedPacman;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private SimpleObjectProperty<Label> welcomeText = new SimpleObjectProperty<Label>(this, "welcomeText");

    @FXML
    protected void onHelloButtonClick() {


        welcomeText.set(new Label("Welcome to JavaFX Application!"));
    }
}