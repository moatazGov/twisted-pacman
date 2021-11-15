module com.hawks.twistedPacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires json.simple;

    opens com.hawks.twistedPacman to javafx.fxml;
    exports com.hawks.twistedPacman;
    exports com.hawks.twistedPacman.View;
    opens com.hawks.twistedPacman.View to javafx.fxml;
    exports com.hawks.twistedPacman.Controller;
    opens com.hawks.twistedPacman.Controller to javafx.fxml;
}