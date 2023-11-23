package controllers;

import Model.Adherant;
import application.ConnectionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdherentController implements Initializable {
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


    public void displayAdherant() { // recupere data from adherent table
        String sql="select * from adherents";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet result=ps.executeQuery(); //fetch
            while(result.next()) {
                observableList.add(new Adherant(result.getInt("id"), result.getString("nom"),result.getString("prenom"), result.getString("tel"),result.getString("email"),result.getString("status"),result.getString("message")));
            } // ou on stocke

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displyon_table(){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displyon_table();
    }
}
