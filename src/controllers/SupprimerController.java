package controllers;

import Model.Adherant;
import Model.Demande;
import application.ConnectionMysql;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SupprimerController implements Initializable {
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


    public void displayAdherant() { //
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

    @FXML
    void SelectAdherant(MouseEvent event) { //  id
        Adherant adherent=demandeTable.getSelectionModel().getSelectedItem();
        String sql="select * from adherents where id='"+adherent.getId()+"'";
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
    public void delete(){ // operation delete
        Adherant adherent=demandeTable.getSelectionModel().getSelectedItem();
        demandeTable.getItems().clear();
        String sql="delete from adherents where id='"+adherent.getId()+"'";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Adherent has been successfully deleted ❤",javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            displayAdherant();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displyon_table();
    }
}
