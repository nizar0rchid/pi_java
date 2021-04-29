/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insc;

import packageentity.Cours;
import packageservice.coursService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ines
 */
public class AnnonceFrontController implements Initializable {

    @FXML
    private Label text;
    @FXML
    private VBox liste_annonce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   try {
           DisplayOne();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

    public void DisplayOne() throws SQLException, IOException {
        coursService ServA = new coursService();
        ArrayList<Cours> TabT = ServA.DisplayNEW();

        for (Cours c : TabT) {
       
            FXMLLoader loader = new FXMLLoader();
            //System.out.println("aaaa");
            loader.setLocation(getClass().getResource("/insc/AnnonceOne.fxml"));
                        //System.out.println("ccc");

            Parent n = (Parent) loader.load();
                       // System.out.println("bbbb");

             insc.AnnonceOneController form = loader.getController();
            form.InitAnnonce(c);
            liste_annonce.getChildren().add(n);
        }
    }
         /*  @FXML
    private void Anonnces(ActionEvent event) throws IOException {
         
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Annonce.fxml")));
        stage.setScene(scene);
        stage.show();
    }*/
}
