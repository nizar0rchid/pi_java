/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;

/**
 *
 * @author Amine
 */
public class DataSource {
    
    private String url="jdbc:mysql://localhost:3306/jobcore";
    private String user="root";
    private String pwd="";
    private Connection cnx;
    private static DataSource instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private DataSource() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            Alert alert = new Alert (Alert.AlertType.NONE,"Erreur de saisie",ButtonType.OK);
            alert.setContentText("Mysql DB connection Successful");
            alert.setTitle("SQL Connection");
            alert.showAndWait();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Mysql DB Connection Failed...");
        }
    }
    
   public static DataSource getInstance(){
       
       if(instance==null)
           instance=new DataSource();
       return instance;
   }
    
    
    
    
}
