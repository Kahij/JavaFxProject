package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane homepane;
    @FXML
    private AnchorPane pane;


    @FXML
    void accueil(MouseEvent event) {
        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Acceuil.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ajouter(MouseEvent event) {
        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Ajouter.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void demandes(MouseEvent event) {
        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Demandes.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifier(MouseEvent event) {
        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Modifier.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void supprimer(MouseEvent event){
        try{
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Supprimer.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void Adherent(MouseEvent event){
        try{
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Adherent.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void logout(){
        try{
            Parent fxml=FXMLLoader.load(getClass().getResource("/interfaces/Main.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(fxml);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            pane.getScene().getWindow().hide();
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/interfaces/Acceuil.fxml"));
            homepane.getChildren().removeAll();
            homepane.getChildren().setAll(fxml);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }



}
