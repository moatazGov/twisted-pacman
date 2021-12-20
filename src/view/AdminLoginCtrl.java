package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginCtrl {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private PasswordField passTxt;
    @FXML
    private TextField usernameTxt;
    @FXML
    private Button backBtn;
    @FXML
    private Button loginBtn;


    @FXML
    void backClicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void loginClicked(ActionEvent event) {
        try {
            if (usernameTxt.getText().length() != 0 && passTxt.getText().length() != 0) {
                if ("Admin".equals(usernameTxt.getText()) && "Admin".equals(passTxt.getText())) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/resources/fxml/questionsAdmin.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    MsgBox.display("Error", "Access Denied!!");
                }
            } else {
                MsgBox.display("Error", "Missing input! Please fill in all fields.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
