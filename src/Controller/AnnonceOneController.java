/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Config.MaConnexion;
import Entities.Categorie;
import Entities.Offre;
//import Entite.Projet;
import Services.ServiceOffre;
//import Service.ServiceProjet;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ines
 */
public class AnnonceOneController implements Initializable {

    @FXML
    private Label Titre;
    @FXML
    private Label salaire;
    @FXML
    private Text description;
    @FXML
    private Label date;
    @FXML
    private Label projet;
    @FXML
    private Label salaire1;
    @FXML
    private Button btnPostuler;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private Label contrat;
    @FXML
    private Label langue;
    @FXML
    private Label niveau;
    @FXML
    private Label experience;
    @FXML
    private Label nbrh;
    @FXML
    private Pane tvpane;
    @FXML
    private ImageView image;
    @FXML
    private TextField idoffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void InitAnnonce(Offre ann) throws SQLException {

        //ServiceProjet sp = new ServiceProjet();
        ServiceOffre sA = new ServiceOffre();
        Offre A = sA.details(ann.getIdOffre());
        //Offre A = sA.getProgramme(ann.getIdOffre());
        Categorie c = A.getCategorie();
        //Projet P = sp.details(A.getProject_id());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String Date = dateFormat.format(A.getDate());

        date.setText(Date);
        //Titre.setText(c.getNom_categorie());
        Titre.setText(A.getNomEntreprise());
        idoffre.setText(String.valueOf(A.getIdOffre()));
        description.setText(A.getDescription());
        email.setText(A.getEmail());
        niveau.setText(A.getNiveauEtude());
        contrat.setText(A.getTypeContrat());
        langue.setText(A.getLangue());
        projet.setText(A.getLocalisation());
        nbrh.setText(String.valueOf(A.getNombreHeure()));
        experience.setText(String.valueOf(A.getExperience()));
        salaire.setText(String.valueOf(A.getSalaire()));
        tel.setText(String.valueOf(A.getTel()));


        /*   if(P.getImage()!=null)
        {
        image.setImage(new Image(getClass().getResource("images/" + P.getImage()).toExternalForm()));
        }*/
    }

    @FXML
    private void addAffectation(ActionEvent event) throws SQLException, Exception {

        Connection cnx = MaConnexion.getinstance().getCnx();

        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Voulez vous postuler à " + Titre.getText() + " ?", ButtonType.YES, ButtonType.CANCEL);
        confirmation.setHeaderText(null);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            String req = "INSERT INTO offre_users (offre_id,users_id) values (?,?)";

            try {

                PreparedStatement stm1 = cnx.prepareStatement(req);
                stm1.setInt(1, Integer.parseInt(idoffre.getText()));
                stm1.setInt(2, 1); // passage statique de id = 1 du user

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Postulation effectuée avec succes !");
                alert.showAndWait();
                stm1.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("probleme");
                System.out.println(ex.getMessage());
            }
        }
    }
}

