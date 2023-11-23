package application;

import java.sql.*;
public class ConnectionMysql {
    public static Connection con=null;
    public static Connection connecttodb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            return con;
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

}

