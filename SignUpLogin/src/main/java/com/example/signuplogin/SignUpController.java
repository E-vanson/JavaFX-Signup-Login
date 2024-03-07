package com.example.signuplogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    Button signup_btn;
    @FXML
    Button btn_login;
    @FXML
    RadioButton rb_water;
    @FXML
    RadioButton rb_fire;
    @FXML
    TextField tf_username;
    @FXML
    TextField tf_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_fire.setToggleGroup(toggleGroup);
        rb_water.setToggleGroup(toggleGroup);

        rb_water.setSelected(true);
        signup_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),toggleName);
                }else{
                    System.out.println("Fill in all info");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill in all fields");
                    alert.show();
                }

            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"Login!","log-in.fxml",null,null);
            }
        });

    }
}
