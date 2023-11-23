package controllers;

import application.ConnectionMysql;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AjouterController implements Initializable {
    public static Connection con= ConnectionMysql.connecttodb();
    @FXML
    private TextField mail;
    @FXML
    private TextField msg;

    @FXML
    private TextField nm;

    @FXML
    private TextField prn;

    @FXML
    private TextField stt;

    @FXML
    private TextField tel;
//create table adhérent (id int primary key AUTO_INCREMENT,nom varchar(20),prenom varchar(20),email varchar(70),tel varchar(20),status varchar(20),message varchar(200));
    @FXML
    public void ajouterAdhérent(){
        String sql="insert into adherents (nom,prenom,email,tel,status,message) values(?,?,?,?,?,?)";
        String nom=nm.getText().toString();
        String prenom=prn.getText().toString();
        String email=mail.getText().toString();
        String phone=tel.getText().toString();
        String status=stt.getText().toString();
        String message=msg.getText().toString();

        try {
            PreparedStatement pstms= con.prepareStatement(sql);
            pstms.setString(1,nom);
            pstms.setString(2,prenom);
            pstms.setString(3,email);
            pstms.setString(4,phone);
            pstms.setString(5,status);
            pstms.setString(6,message);
            pstms.executeUpdate();
            clearchamp();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"your account has been created successfully ❤",javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clearchamp(){
        nm.setText(null);
        prn.setText(null);
        mail.setText(null);
        stt.setText(null);
        tel.setText(null);
        msg.setText(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
