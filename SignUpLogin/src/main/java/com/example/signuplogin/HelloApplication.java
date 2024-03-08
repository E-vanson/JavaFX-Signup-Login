package com.example.signuplogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}