/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Affectation;
import Entities.Offre;
import Services.ServiceAffectation;
import Services.ServiceOffre;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterAffectationController implements Initializable {

    @FXML
    private TextField txtidoffre;
    @FXML
    private Button btnPostuler;
    @FXML
    private TableView<Affectation> tvAffectation;
    @FXML
    private TableColumn<Affectation, Integer> colidoffre;
    @FXML
    private TableColumn<Affectation, Integer> coliduser;
    @FXML
    private TableView<Offre> tvoffre;
    @FXML
    private TableColumn<Offre, Integer> colid;
    @FXML
    private TableColumn<Offre, String> colnomentreprise;
    @FXML
    private TableColumn<Offre, Integer> colsalaire;
    @FXML
    private TableColumn<Offre, String> coldescription;
    @FXML
    private TableColumn<Offre, String> collocalisation;
    @FXML
    private TableColumn<Offre, Integer> colnbrheure;
    @FXML
    private TableColumn<Offre, String> colcontrat;
    @FXML
    private TableColumn<Offre, Integer> colniveauetude;
    @FXML
    private TableColumn<Offre, String> colexperience;
    @FXML
    private TableColumn<Offre, String> collangue;
    @FXML
    private TableColumn<Offre, Date> coldate;
    @FXML
    private TableColumn<Offre, Integer> coltel;
    @FXML
    private TableColumn<Offre, String> colemail;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadAffectation();
        loadOffre();
    }    

    @FXML
    private void addAffectation(ActionEvent event) {
        
        if(txtidoffre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
        else
        {
        Integer idoffre = Integer.parseInt(txtidoffre.getText());
        Affectation c = new Affectation(idoffre,1);
        ServiceAffectation sc = new ServiceAffectation();
        sc.ajouterAffectation(c);
                    JOptionPane.showMessageDialog(null, "ajout avec succes");
        txtidoffre.clear();
       
        loadAffectation();
         }
    }
    
    private void loadAffectation() { 
            ServiceAffectation ps = new ServiceAffectation();
        ArrayList<Affectation> publiciteList =  (ArrayList<Affectation>) ps.getAffectation() ;
    ObservableList<Affectation> donnee = FXCollections.observableArrayList(publiciteList); 
    tvAffectation.setItems(donnee);
        colidoffre.setCellValueFactory(new PropertyValueFactory<>("IdOffre"));
        coliduser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));     
        }
        
     private void loadOffre() {
        
            ServiceOffre ps = new ServiceOffre();
        ArrayList<Offre> publiciteList =  (ArrayList<Offre>) ps.getOffre() ;
    ObservableList<Offre> donnee = FXCollections.observableArrayList(publiciteList); 
    tvoffre.setItems(donnee);
        colid.setCellValueFactory(new PropertyValueFactory<>("IdOffre"));
        colnomentreprise.setCellValueFactory(new PropertyValueFactory<>("NomEntreprise"));
        colsalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        collocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        colnbrheure.setCellValueFactory(new PropertyValueFactory<>("nombreHeure"));
        colexperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        colcontrat.setCellValueFactory(new PropertyValueFactory<>("typeContrat"));
        colniveauetude.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        collangue.setCellValueFactory(new PropertyValueFactory<>("langue"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        

        FilteredList<Offre> filteredData = new FilteredList<>(donnee,b-> true);
        
          search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(offre -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (offre.getNomEntreprise().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches nom entreprise.
				} 
                                 else if (offre.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches description.
				}
                                 else if (offre.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches description.
				}
                                 else if (offre.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches email.
				}
                                 else if (offre.getLocalisation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches localisation.
				} 
                                 else if (offre.getTypeContrat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches contrat.
				}    
                                 else if (offre.getNiveauEtude().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches niv etude.
				}    
                                 else if (offre.getLangue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches langue.
				}   
                                 else if (String.valueOf(offre.getDate()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches date.
				}
                                 else if (String.valueOf(offre.getTel()).indexOf(lowerCaseFilter)!=-1){  
                                        return true;
                                }
                                 else if (String.valueOf(offre.getExperience()).indexOf(lowerCaseFilter)!=-1){
                                        return true;
                                }
                                 else if (String.valueOf(offre.getSalaire()).indexOf(lowerCaseFilter)!=-1){ 
                                        return true;
                                }
                                 else if (String.valueOf(offre.getIdOffre()).indexOf(lowerCaseFilter)!=-1){ 
                                        return true;
                                }
				else if (String.valueOf(offre.getNombreHeure()).indexOf(lowerCaseFilter)!=-1)
                                        return true;
				     else  
                                         return false; // Does not match.
			});
		});
          // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Offre> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvoffre.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		
               tvoffre.setItems(sortedData);

        
        }
    
}
