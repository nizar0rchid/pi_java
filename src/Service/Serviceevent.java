/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.event;
import tools.Connexion;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author user
 */
public class Serviceevent {
    Connection cnx;
    private List<event> event;
    private PreparedStatement pre;
    private Statement ste;
    public Serviceevent() {
cnx =Connexion.getInstance().getConnection();
        }  
    
   public void Addevent(event t) throws SQLException  {
  
        String req ="INSERT INTO event (nom,lieu,date,capacite,description,capaciteinitiale) values (?,?,?,?,?,?)";
        
        try {
            
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setString(1, t.getNom());
             stm.setString(2, t.getLieu());
             stm.setDate(3,t.getDate());
             stm.setInt(4,t.getCapacite());
             stm.setString(5, t.getDescription());
             stm.setInt(6,t.getCapacite());
             
             stm.executeUpdate();
             System.out.println("Evenment ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }
    public List<event> afficherevent() throws SQLException {
     List<event> resulta = new ArrayList<>();

     Statement stm=cnx.createStatement();
     String query="select * from event ";
     
ResultSet resultat = stm.executeQuery(query) ;   
               event t=new event();
       while(resultat.next()) {
           t.setId(resultat.getInt("id"));
             t.setNom(resultat.getString("nom"));
                 t.setLieu(resultat.getString("lieu"));              
               t.setDate(resultat.getDate("date"));
               t.setCapacite(resultat.getInt("capacite"));
               t.setDescription(resultat.getString("description"));
               
                                     
               resulta.add(t);
       } 

       return resulta;   
 }
      public void supprimerevent(event t){
       String req="delete from event where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,t.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " Evnement suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
       public int Modifierevent(int id,event t) throws SQLException {
        if(chercher(id)){
        
        pre=cnx.prepareStatement("UPDATE event SET nom = ? , lieu = ? , date = ? , capacite = ? , description = ? , capaciteinitiale = ? WHERE id = "+id+"");
    try{     
             pre.setString(1, t.getNom());
             pre.setString(2, t.getLieu());
             pre.setDate(3,t.getDate());
             pre.setInt(4,t.getCapacite());
             pre.setString(5, t.getDescription());
             pre.setInt(6,t.getCapacite());
    
    
    pre.executeUpdate();
    }
    catch (SQLException m){
      System.out.println(m.getMessage());  
    }
    return 1;}
        return 0;
    }
      
      
      
      
      public boolean chercher(int id) throws SQLException {
        String req="select * from event";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
      
      
      
      public ObservableList<event> FindEvent() {

        ObservableList<event> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM event";
            Statement st5 = Connexion.getInstance().getConnection.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                event e = new event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setCapacite(rs.getInt("capacite"));
                e.setDescription(rs.getString("description"));
          
              
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }
      
    public List<event> getAll() {
ObservableList<event> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                event e = new event();
                e.setId(rs.getInt(1));
              e.setNom(rs.getString("nom"));
                 e.setLieu(rs.getString("lieu"));              
               e.setDate(rs.getDate("date"));
               e.setCapacite(rs.getInt("capacite"));
               e.setDescription(rs.getString("description"));

                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
    
    
    
    public ObservableList<event> read() throws SQLException{
            ObservableList<event> L = FXCollections.observableArrayList();

    Statement st = cnx.createStatement();
    String req = "select * from event";
    ResultSet rs = st.executeQuery(req);

    while(rs.next()){
        int id = rs.getInt(1);
        String nom = rs.getString("nom");
        String lieu = rs.getString("lieu");
        Date date = rs.getDate("date");
        int capacite = rs.getInt("capacite");
        String description = rs.getString("description");
        


        event p= new event(id,nom,lieu,date,capacite,description);

        L.add(p);
    }

    return L;

    }
}
