/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import Config.MaConnexion;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class StatController implements Initializable {
    ObservableList<PieChart.Data> piechartdata;
    Connection cnx = MaConnexion.getinstance().getCnx();   

    @FXML
    private PieChart stat_offre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat_offre.setData(piechartdata);
        
    }  
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "select o.type_contrat,count(o.id) as nbdep from offre o group by o.type_contrat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("type_contrat"), rs.getInt("nbdep")));

        }
    }
    
    
}
