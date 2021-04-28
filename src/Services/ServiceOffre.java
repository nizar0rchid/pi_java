/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offre;
import Config.MaConnexion;
import Entities.Categorie;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class ServiceOffre {
    
     Statement stx;
     Connection cnx;
    PreparedStatement ste;
    
    public ServiceOffre(){
        cnx = MaConnexion.getinstance().getCnx();
    }
   /* public  Offre getProgramme(int id)  {
     Offre p = null ;
          try {

              String sql = "SELECT p.id as pid,p.nom_entreprise as pnom,salaire,description,localisation,nombre_heure,type_contrat,niveau_etude,experience,langue,date_expiration,tel,email,l.* FROM offre p JOIN categorie l on(p.nom_categorie_id=l.id)WHERE p.id= ?";
              PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(sql);
              preparedStatement.setInt(1, id);

              ResultSet rs = preparedStatement.executeQuery();

              while (rs.next()) {
          Categorie categ = new Categorie(rs.getInt("id"),rs.getString("nom_categorie"),rs.getString("color"));
              p =  new Offre(rs.getInt("id"), rs.getString("nom_entreprise"), rs.getInt("salaire"), rs.getString("description"), rs.getString("localisation"), rs.getInt("nombre_heure"), rs.getString("type_contrat"), rs.getString("niveau_etude"), rs.getInt("experience"), rs.getString("langue"), rs.getDate("date_expiration"), rs.getInt("tel"), rs.getString("email"),categ);
                  return p;
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
          }
          return p ;
    }*/
    public void ajouterOffre(Offre o){
         try {
        String sql = "insert into offre( nom_categorie_id, nom_entreprise, salaire, description, localisation, nombre_heure, type_contrat, niveau_etude, experience, langue, date_expiration, tel, email)"+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ste = cnx.prepareStatement(sql);
        //ste.setInt(1,o.);
        ste.setInt(1, o.getCateg());
        ste.setString(2, o.getNomEntreprise());
        ste.setInt(3, o.getSalaire());
        ste.setString(4, o.getDescription());
        ste.setString(5, o.getLocalisation());
        ste.setInt(6, o.getNombreHeure());
        ste.setString(7, o.getTypeContrat());
        ste.setString(8, o.getNiveauEtude());
        ste.setInt(9, o.getExperience());
        ste.setString(10, o.getLangue());
        ste.setDate(11, o.getDate());
        ste.setInt(12, o.getTel());
        ste.setString(13, o.getEmail());

        ste.executeUpdate();
             System.out.println("Offre Ajoutée");
         }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierOffre(Offre o){
        String req ="UPDATE offre SET nom_categorie_id='"+o.getCateg()+"',nom_entreprise='"+o.getNomEntreprise()+"',salaire='"+o.getSalaire()+"',description='"+o.getDescription()+"',localisation='"+o.getLocalisation()+"',nombre_heure='"+o.getNombreHeure()+"',type_contrat='"+o.getTypeContrat()+"',niveau_etude='"+o.getNiveauEtude()+"',experience='"+o.getExperience()+"',langue='"+o.getLangue()+"',date_expiration='"+o.getDate()+"',tel='"+o.getTel()+"',email='"+o.getEmail()+"' WHERE id="+o.getIdOffre() ;
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("Offre modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
         
    }
    
    public void supprimerOffre(Offre o){
        String requete = "DELETE FROM offre WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, o.getIdOffre());
            pst.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        System.out.println("Offre supprimée!");
    }
    
     public List<Offre> getOffre(){
        List<Offre> list = new ArrayList<>();
        String requete = "SELECT * FROM offre ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Offre o = new Offre(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getInt(13), rs.getString(14));               
                list.add(o);
            }
            System.out.println("List des offres à été crée!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public ArrayList<Offre> DisplayNEW() throws SQLException {
        ArrayList<Offre> TabA = new ArrayList<>();
        String req = "SELECT * FROM offre ";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

             TabA.add(new Offre(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getInt(13), rs.getString(14)));

        }
        return TabA;
    }
    public Offre details(int id) throws SQLException {
       String req = "select * from offre where id ='" + id + "'";

        Offre A = null;

        try {
            stx = cnx.createStatement();
            ResultSet rs = stx.executeQuery(req);
            while (rs.next()) {
                //java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
     
           A = new Offre(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getInt(13), rs.getString(14));               
      
           //A= new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), sqlDate,rs.getString(7));
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ServiceProjet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return A;
    }
     
}
