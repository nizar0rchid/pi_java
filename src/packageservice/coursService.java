/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageservice;

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
import packageentity.Cours;
import packageutils.DataSource;

/**
 *
 * @author Amine
 */
public class coursService implements iservice<Cours> {

    private static coursService instance;
    private Statement st;
    Statement stx;
    //private ResultSet rs;
    Connection cnx;

    public coursService() {
        cnx = DataSource.getInstance().getCnx();

        DataSource cs = DataSource.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static coursService getInstance() {
        if (instance == null) {
            instance = new coursService();
        }
        return instance;
    }

    @Override
    public void insert(Cours c) {
        String req = "insert into cours (nom_complet_cours,nom_aberge_cours,date_debut_cours,date_fin_cours,category,Categorie,image) values ('" + c.getTitle() + "','" + c.getAuthor() + "','" + c.getDate() + "','" + c.getDate() + "','" + c.getCategory() + "','" + c.getUrl() + "','" + c.getImage() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Cours c) {
        String req = "delete from cours where id=" + c.getId();
        Cours p = displayById(c.getId());

        if (p != null)
              try {

            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public boolean update(Cours p) {
        String qry = "UPDATE cours SET nom_complet_cours = '" + p.getTitle() + "', nom_aberge_cours = '" + p.getAuthor() + "',date_debut_cours = '" + p.getDate() + "' ,date_fin_cours = '" + p.getDate() + "' ,category = '" + p.getCategory() + "' , Categorie = '" + p.getUrl() + "',image = '" + p.getImage() + "' WHERE id = " + p.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ObservableList<Cours> displayAll() {
        String req = "select * from cours";
        ObservableList<Cours> list = FXCollections.observableArrayList();

        try {
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cours p = new Cours();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setAuthor(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getString(5));
                p.setCategory(rs.getString(6));

                p.setUrl(rs.getString(8));
                p.setImage(rs.getString(7));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Cours> displayAllList() {
        String req = "select * from cours";
        List<Cours> list = new ArrayList<>();

        try {
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cours p = new Cours();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString("nom_complet_cours"));
                p.setAuthor(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setDate(rs.getString(5));
                p.setCategory(rs.getString(6));

                p.setUrl(rs.getString(8));
                p.setImage(rs.getString(7));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Cours displayById(int id) {
        String req = "select * from cours where id =" + id;
        Cours p = new Cours();
        try {
            ResultSet rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("nom_complet_cours"));
            p.setAuthor(rs.getString("nom_aberge_cours"));
            p.setDescription(rs.getString("date_debut_cours"));
            p.setDate(rs.getString("date_fin_cours"));
            p.setCategory(rs.getString("category"));

            p.setUrl(rs.getString("Categorie"));
            p.setImage(rs.getString("image"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(coursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public ArrayList<Cours> DisplayNEW() throws SQLException {
        ArrayList<Cours> TabA = new ArrayList<>();
        String req = "SELECT * FROM cours ";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabA.add(new Cours(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));

        }
        return TabA;
    }

    public Cours details(int id) throws SQLException {
        String req = "select * from cours where id ='" + id + "'";

        Cours A = null;

        try {
            stx = cnx.createStatement();
            ResultSet rs = stx.executeQuery(req);
            while (rs.next()) {
                //java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());

                A = new Cours(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

                //A= new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5), sqlDate,rs.getString(7));
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ServiceProjet.class.getName()).log(Level.SEVERE, null, ex);

            
        }
        return A;
    }
}