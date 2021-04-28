/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.MaConnexion;
import Entities.Affectation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServiceAffectation {
    
      Connection cnx;
    PreparedStatement ste;
    
    public ServiceAffectation(){
        cnx = MaConnexion.getinstance().getCnx();
    }
    
     public void ajouterAffectation(Affectation c){
         try {
        String sql = "insert into offre_users(offre_id,users_id)"+"values(?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, c.getIdOffre());
        ste.setInt(2, 1);
        //ste.setInt(2, c.getIdUser());
        ste.executeUpdate();
             System.out.println("Affectation Ajoutée");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    public List<Affectation> getAffectation(){
        List<Affectation> list = new ArrayList<>();
        String requete = "SELECT * FROM offre_users ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Affectation c = new Affectation(rs.getInt(1), rs.getInt(2));                
                list.add(c);
            }
            System.out.println("List des Affectations à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
    
}
