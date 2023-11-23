package controllers;

import application.ConnectionMysql;
import com.mysql.jdbc.Blob;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
public class AcceuilController implements Initializable {
    public Connection con= ConnectionMysql.connecttodb();
    private FileInputStream fstream;
    @FXML
    private Label lab_url;
    private FileInputStream filestream;
    @FXML
    private ImageView uploading_image;
    @FXML
    void getImage(){
        String sql="select * from admins";
        byte byteImg[];
        Blob blob;
        try {
            PreparedStatement pstms=(PreparedStatement) con.prepareStatement(sql);
            ResultSet result=pstms.executeQuery();
            while(result.next()) {
                blob=(Blob) result.getBlob("image");
                byteImg=blob.getBytes(1,(int) blob.length());
                Image img=new Image(new ByteArrayInputStream(byteImg),uploading_image.getFitWidth(),uploading_image.getFitHeight(),true,true);
                uploading_image.setImage(img);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateImage() { //changer la pdp
        File image = new File(lab_url.getText());
        try{
            String sql="UPDATE admins SET image=?";
            PreparedStatement pstms=(PreparedStatement) con.prepareStatement(sql);
            filestream=new FileInputStream(image);
            pstms.setBinaryStream(1,filestream,image.length());
            pstms.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    void uploadphoto(){ //telecharger la photo (this step becomes before the update)
        FileChooser fl=new FileChooser();
        fl.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jfif"));
        File file=fl.showOpenDialog(null);
        if(file!=null) {
            lab_url.setText(file.getAbsolutePath());
            Image img = new Image(file.toURI().toString(), uploading_image.getFitWidth(), uploading_image.getFitHeight(), true, true);
            uploading_image.setImage(img);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getImage();
    }
}
