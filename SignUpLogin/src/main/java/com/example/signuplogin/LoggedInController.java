package com.example.signuplogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button btn_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_secret;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"Sign Up!", "sign-up.fxml",null,null);

            }
        });

    }

    public void setUserInfo(String username, String secret){
        label_welcome.setText("Welcome " + username + "!");
        label_secret.setText("You choose the secret " + secret);
    }
}
