/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insc;

import packageentity.Cours;
import packageservice.coursService;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import packageutils.DataSource;

/**
 * FXML Controller class
 *
 * @author ines
 */
public class AnnonceOneController implements Initializable {

    @FXML
    private Label Titre;
    @FXML
    private Text description;
    @FXML
    private Label date;
    @FXML
    private Button btnPostuler;
    @FXML
    private Label email;
    @FXML
    private Hyperlink langue;
    @FXML
    private Label niveau;
    @FXML
    private Pane tvpane;
    @FXML
    private ImageView image;
    @FXML
    private TextField idcours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void InitAnnonce(Cours ann) throws SQLException {

       //ServiceProjet sp = new ServiceProjet();
       coursService sA = new coursService();
        Cours A = sA.details(ann.getId());
        //Projet P = sp.details(A.getProject_id());
      
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String Date = dateFormat.format(A.getDate());
         idcours.setText(String.valueOf(A.getId()));
        date.setText(A.getDate());
    
        Titre.setText(A.getTitle());
        description.setText(A.getDescription());
        email.setText(A.getAuthor());
        niveau.setText(A.getCategory());
        
        langue.setText(A.getImage());
        

 /*if(A.getUrl()!=null)
        {
        image.setImage(new Image(getClass().getResource("C:\\Users\\ali\\Desktop\\PIDEV-amine-main\\img\\img" + A.getUrl()).toExternalForm()));
        }*/
        
      image.setImage(new Image( A.getUrl()));
       /*Pane p = new HBox();
       Image img= new Image(A.getUrl());
       p.getChildren().add(new ImageView(img));*/
       
    }

    @FXML
    private void addAffectation(ActionEvent event) throws SQLException, Exception{
Connection cnx = DataSource.getInstance().getCnx();

        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Voulez vous Inscrire à " + Titre.getText() + " ?", ButtonType.YES, ButtonType.CANCEL);
        confirmation.setHeaderText(null);
        confirmation.showAndWait();

        if (confirmation.getResult() == ButtonType.YES) {
            String req = "INSERT INTO inscription (id_cours_id , id_user_id) values (?,?)";

            try {

                PreparedStatement stm1 = cnx.prepareStatement(req);
                stm1.setInt(1, Integer.parseInt(idcours.getText()));
                stm1.setInt(2, 29); // passage statique de id = 1 du user

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Inscription effectuée avec succes !");
                alert.showAndWait();
                stm1.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("probleme");
                System.out.println(ex.getMessage());
            }
        }

    }
    
}
