/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageservice;


import IService.IinscripService;
import packageentity.inscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageutils.DataSource;


/**
 *
 * @author ali
 */
public class InscriService implements IinscripService {
    Connection cnx;
    private Statement ste;
    private PreparedStatement pre;
    public InscriService() {
        cnx = DataSource.getInstance().getCnx();}
    
    public void createEvenement(inscription e) {
           try {
            String requete2 = "INSERT INTO inscrip (cour_nom,cour_email)"+" VALUES (?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete2);
            pst.setString(1, e.getCour_nom());
            pst.setString(2, e.getCour_email());
            pst.executeUpdate();
            System.out.println("inscri added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void update(inscription e,int id) {
 try {
     
            System.out.println(e.toString());
            System.out.println(id);            
            String requete3 = "UPDATE inscrip SET cour_nom=?,cour_email=? WHERE id=?";
            PreparedStatement pst2 = DataSource.getInstance().getCnx().prepareStatement(requete3);
           
            pst2.setString(1, e.getCour_nom());
            pst2.setString(2, e.getCour_email());
            pst2.setInt(3,id);
             pst2.executeUpdate();
            System.out.println("Transport updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM inscrip WHERE id=?";
            PreparedStatement pst7 = DataSource.getInstance().getCnx().prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<inscription> getAll() {
ObservableList<inscription> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM inscrip";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                inscription e = new inscription();
                e.setId(rs.getInt(1));
                e.setCour_nom(rs.getString("cour_nom"));
                  e.setCour_email(rs.getString("cour_email"));
                    
               

                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
    
    public ObservableList<inscription> FindEvent() {

        ObservableList<inscription> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM inscrip";
            Statement st5 = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                inscription e = new inscription();
                e.setId(rs.getInt("id"));
                e.setCour_nom(rs.getString("cour_nom"));
                
                e.setCour_email(rs.getString("cour_email"));
                
              
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }
    
    public boolean chercher_ajout(inscription t) throws SQLException {
  String req="select * from inscrip where id= '"+t.getId()+ "'";
        List<inscription> list = new ArrayList<>();
       
        try {
            ste=cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                 java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new inscription(rs.getString(1),rs.getString(2)));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (list.size()!=0);
    }    
            
}
