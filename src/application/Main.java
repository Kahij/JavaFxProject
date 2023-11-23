package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javafx.stage.StageStyle.TRANSPARENT;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage)  {

        try{
            primaryStage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Main.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
            primaryStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(TRANSPARENT);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        launch(args);
    }
}
