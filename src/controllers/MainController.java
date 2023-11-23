package controllers;

import application.ConnectionMysql;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.stage.StageStyle.TRANSPARENT;

public class MainController implements Initializable {

    public Connection con= ConnectionMysql.connecttodb();
    @FXML
    private TextField email,name,pw;
    @FXML
    private VBox vbox;
    @FXML
    private Pane pane;
    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void connect() {
        int nbe=0;
        String login=username.getText();
        String pass=password.getText();
        try{
            String sql="select * from admins where username='"+login+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result=ps.executeQuery();
            if(result.next()){
                if(login.equals(result.getString("username")) && pass.equals(result.getString("password"))){
                    try {
                        Stage home = new Stage();
                        Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
                        Scene scene = new Scene(fxml);
                        home.initStyle(TRANSPARENT);
                        home.setScene(scene);
                        home.show();
                        //pane.getScene().getWindow().hide();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                nbe++;
            }
            if(nbe==0){
                Alert alert = new Alert(Alert.AlertType.ERROR,"username or password incorrect !!");
                alert.showAndWait();
            }
            }catch(Exception e){
                e.printStackTrace();
            }
    }


    @FXML
    void getUser() {
            String sql="insert into adherents (nom,email,password) values(?,?,?)";
            String nom=name.getText().toString();
            String em=email.getText().toString();
            String password=pw.getText().toString();
            try {
                PreparedStatement pstms= con.prepareStatement(sql);
                pstms.setString(1,nom);
                pstms.setString(2,em);
                pstms.setString(3,password);
                pstms.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"your account has been created successfully ❤",javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            }catch(Exception e) {
                e.printStackTrace();
            }

        }

    @FXML
    void singin() {
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*8.5);
        t.play();
        t.setOnFinished(e->{
            try{
                Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        });
    }

    @FXML
    void singup() {
        TranslateTransition t= new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutY()*0.5);
        t.play();
        t.setOnFinished(e->{
            try{
                Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
