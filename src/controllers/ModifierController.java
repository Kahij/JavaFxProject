package controllers;

import Model.Adherant;
import Model.Demande;
import application.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ModifierController implements Initializable {
    Connection con= ConnectionMysql.connecttodb();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    public ObservableList<Adherant> observableList = FXCollections.observableArrayList();

    @FXML
    private Label name,num;
    @FXML
    private TableView<Adherant> demandeTable;
    @FXML
    private TableColumn<Adherant, String> email_d,message_d;
    @FXML
    private TableColumn<Adherant, Integer> id_d;
    @FXML
    private TableColumn<Adherant, String> nom_d;
    @FXML
    private TableColumn<Adherant, String> prenom_d;
    @FXML
    private TableColumn<Adherant, String> status_d;
    @FXML
    private TableColumn<Adherant, String> tel_d;
    @FXML
    private TextField mail,pm;
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

    public void displayAdherant() {
        String sql="select * from adherents";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet result=ps.executeQuery(); //fetch
            while(result.next()) {
                observableList.add(new Adherant(result.getInt("id"), result.getString("nom"),result.getString("prenom"), result.getString("tel"),result.getString("email"),result.getString("status"),result.getString("message")));
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displyon_table(){ // getting data from database
        displayAdherant();
        id_d.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_d.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_d.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel_d.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email_d.setCellValueFactory(new PropertyValueFactory<>("email"));
        status_d.setCellValueFactory(new PropertyValueFactory<>("status"));
        message_d.setCellValueFactory(new PropertyValueFactory<>("message"));
        demandeTable.setItems(observableList); //stockage des data into table view
    }

    @FXML
    void SelectAdherent(MouseEvent event) {
        Adherant adherent=demandeTable.getSelectionModel().getSelectedItem();
        String sql="select * from adherents where id='"+adherent.getId()+"'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                nm.setText(result.getString("nom"));
                prn.setText(result.getString("prenom"));
                mail.setText(result.getString("email"));
                stt.setText(result.getString("status"));
                tel.setText(result.getString("tel"));
                msg.setText(result.getString("message"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void updateAd(MouseEvent envent){ //operation mise a jour
        Adherant adherent=demandeTable.getSelectionModel().getSelectedItem();
        String sql=("update adherents set nom=?,prenom=?,email=?,tel=?,status=?,message=? where id='"+adherent.getId()+"'" );
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Adherent has been updated successfully ❤",javafx.scene.control.ButtonType.OK);
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
        displyon_table();
    }
}
