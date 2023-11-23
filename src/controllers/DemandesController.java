package controllers;

import Model.Demande;
import application.ConnectionMysql;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DemandesController implements Initializable {
    Connection con= ConnectionMysql.connecttodb();
    public ObservableList<Demande> observableList = FXCollections.observableArrayList();


    @FXML
    private Label name,num;
    @FXML
    private TableView<Demande> demandeTable;

    @FXML
    private TableColumn<Demande, String> email_d,message_d;

    @FXML
    private TableColumn<Demande, Integer> id_d;

    @FXML
    private TableColumn<Demande, String> nom_d;

    @FXML
    private TableColumn<Demande, String> prenom_d;

    @FXML
    private TableColumn<Demande, String> status_d;

    @FXML
    private TableColumn<Demande, String> tel_d;


    public void displayDemande() { // recupere les data from demande table<
        String sql="select * from demandes";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet result=ps.executeQuery();
            while(result.next()) {
                observableList.add(new Demande(result.getInt("id"), result.getString("nom"),result.getString("prenom"), result.getString("tel"),result.getString("email"),result.getString("status"),result.getString("message")));
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displyon_table(){
            displayDemande();
            id_d.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom_d.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom_d.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tel_d.setCellValueFactory(new PropertyValueFactory<>("tel"));
            email_d.setCellValueFactory(new PropertyValueFactory<>("email"));
            status_d.setCellValueFactory(new PropertyValueFactory<>("status"));
            message_d.setCellValueFactory(new PropertyValueFactory<>("message"));
            demandeTable.setItems(observableList);
    }

    @FXML
    void SelectDemande(MouseEvent event) { // qui s'affiche en bas :: id et num
        Demande demande=demandeTable.getSelectionModel().getSelectedItem();
        String sql="select * from demandes where id='"+demande.getId()+"'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                num.setText(result.getString("id"));
                name.setText(result.getString("nom"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void accepterDemande(){
        Demande demande=demandeTable.getSelectionModel().getSelectedItem();
        demandeTable.getItems().clear();
        String sql="insert into adherents (nom,prenom,email,tel,status,message) values(?,?,?,?,?,?)";
        String nom=demande.getNom();
        String prenom=demande.getPrenom();
        String email=demande.getEmail();
        String phone=demande.getTel();
        String status=demande.getStatus();
        String message=demande.getMessage();
        try {
            PreparedStatement pstms= con.prepareStatement(sql);
            pstms.setString(1,nom);
            pstms.setString(2,prenom);
            pstms.setString(3,email);
            pstms.setString(4,phone);
            pstms.setString(5,status);
            pstms.setString(6,message);
            pstms.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Demande has been accepted successfully ❤",javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            String sqla="delete from demandes where id='"+demande.getId()+"'";
            try{
                PreparedStatement ps=con.prepareStatement(sqla);
                ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }
            displyon_table();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displyon_table();
    }
}
