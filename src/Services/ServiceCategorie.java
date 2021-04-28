/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Config.MaConnexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class ServiceCategorie {
    
    Connection cnx;
    PreparedStatement ste;
    
    public ServiceCategorie(){
        cnx = MaConnexion.getinstance().getCnx();
    }
    
    public void ajouterCategorie(Categorie c){
         try {
        String sql = "insert into categorie(nom_categorie,color)"+"values(?,?)";
        ste = cnx.prepareStatement(sql);
        ste.setString(1, c.getNom_categorie());
        ste.setString(2, c.getColor());
        ste.executeUpdate();
             System.out.println("Categorie Ajoutée");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierCategorie(Categorie c){
      String req ="UPDATE categorie SET nom_categorie='"+c.getNom_categorie()+"',color='"+c.getColor()+"' WHERE id="+c.getId() ;
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("Categorie modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        /*  String requete = "UPDATE categorie  SET nom_categorie = ?, color = ? WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom_categorie());
            pst.setString(2, c.getColor());
            pst.setInt(3, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Categorie modifée!");*/
    }
    
    public void supprimerCategorie(Categorie c){
        String requete = "DELETE FROM categorie WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("Categorie supprimée!");
    }
    
     public List<Categorie> getCategories(){
        List<Categorie> list = new ArrayList<>();
        String requete = "SELECT * FROM categorie ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));                
                list.add(c);
            }
            System.out.println("List des categories à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
     
    public ArrayList<Categorie> DisplayAll() throws SQLException {
        ArrayList<Categorie> TabP = new ArrayList<>();
        String req = "SELECT * FROM categorie";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) { 

            TabP.add(new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return TabP;
    }
    
}
