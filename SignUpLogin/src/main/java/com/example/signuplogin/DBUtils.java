package com.example.signuplogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class DBUtils {
    public static void changeScene(ActionEvent event, String title, String fxmlFile, String username, String chosenScrt){
        Parent root = null;

        if(username != null && chosenScrt != null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInfo(username,chosenScrt);

            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,700,700));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String chosenScrt){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration","","");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM `User-Info` WHERE user_name = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User Exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Can't use the username");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO `User-Info` (user_name, password, fav_channel) VALUES (?, ?, ?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,chosenScrt);
                psInsert.executeUpdate();

                changeScene(event,"Welcome "+ username + "!","logged-in.fxml", username, chosenScrt);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try{
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loginUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration","","");
            preparedStatement = connection.prepareStatement("SELECT password, fav_channel FROM `User-Info` WHERE user_name = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Username not found in DB");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide correct details!");
                alert.show();
            }else{
                while(resultSet.next()){
                    String retrivePassword = resultSet.getString("password");
                    String retrivedChannel = resultSet.getString("fav_channel");
                    if(retrivePassword.equals(password)){
                        changeScene(event,"Welcome!","logged-in.fxml",username,retrivedChannel);
                    }else {
                        System.out.println("Passwords Didn't match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect details!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
