/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Categorie;
import Entities.Offre;
import Services.ServiceCategorie;
import Services.ServiceOffre;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import views.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminOffreController implements Initializable {

    @FXML
    private TableView<Offre> tvoffre;
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
    private TableColumn<Offre, Integer> colexperience;
    @FXML
    private TableColumn<Offre, String> colcontrat;
    @FXML
    private TableColumn<Offre, String> colniveauetude;
    @FXML
    private TableColumn<Offre, String> collangue;
    @FXML
    private TableColumn<Offre, Integer> coltel;
    @FXML
    private TableColumn<Offre, String> colemail;
    @FXML
    private TableColumn<Offre, Date> coldate;
    @FXML
    private TextField txtnomentreprise;
    @FXML
    private TextField txtsalaire;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtlocalisation;
    @FXML
    private TextField txtnbrheure;
    @FXML
    private TextField txtexperience;
    @FXML
    private TextField txtnivetude;
    @FXML
    private TextField txtlangue;
    @FXML
    private TextField txttel;
    @FXML
    private TextField txtemail;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtcontrat;
    @FXML
    private TextField search;
    @FXML
    private ImageView emailCheckMark;
        Notifications n;
        String erreur;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private ImageView langueCheckMark;
    @FXML
    private ImageView contratCheckMark;
    @FXML
    private ImageView expCheckMark;
    @FXML
    private ImageView heureCheckMark;
    @FXML
    private ImageView localCheckMark;
    @FXML
    private ImageView descCheckMark;
    @FXML
    private ImageView salaireCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private Button btnstat;
    @FXML
    private ComboBox<Categorie> id_projet;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         javafx.util.Callback<ListView<Categorie>, ListCell<Categorie>> cellFactory = new javafx.util.Callback<ListView<Categorie>, ListCell<Categorie>>() {

            @Override
            public ListCell<Categorie> call(ListView<Categorie> l) {
                return new ListCell<Categorie>() {

                    @Override
                    protected void updateItem(Categorie item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNom_categorie());
                        }
                    }
                };
            }
        };
          id_projet.setButtonCell(cellFactory.call(null));
        id_projet.setCellFactory(cellFactory);
         ServiceCategorie sp = new ServiceCategorie();
        List<Categorie> arr = new ArrayList<>();
        try {
            arr = sp.DisplayAll();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Categorie p : arr) {
            String titre = p.getNom_categorie();
     
            id_projet.getItems().add(p);
        }
        load();
    }    

    @FXML
    private void addOffre(ActionEvent event)throws Exception {
        String date = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(!testSaisie())
       // if(txtnomentreprise.getText().isEmpty()||txtsalaire.getText().isEmpty()||txtdescription.getText().isEmpty()||txtlocalisation.getText().isEmpty()||txtnbrheure.getText().isEmpty()||txtnivetude.getText().isEmpty()||txtlangue.getText().isEmpty()||date.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");  
        }
        else
        {
         String nomentreprise = txtnomentreprise.getText();
         Integer salaire = Integer.parseInt(txtsalaire.getText());
         String description = txtdescription.getText();
         String localisation = txtlocalisation.getText();
         Integer nbrheure = Integer.parseInt(txtnbrheure.getText());
         Integer experience = Integer.parseInt(txtexperience.getText());
         String nivetude = txtnivetude.getText();
         String langue = txtlangue.getText();
         String email = txtemail.getText();
         Integer tel = Integer.parseInt(txttel.getText());
         Date dateExpiration=java.sql.Date.valueOf(datepicker.getValue());
         String contrat = txtcontrat.getText();         
         
        Offre c = new Offre(5,id_projet.getValue().getId(),nomentreprise,salaire,description,localisation,nbrheure,contrat,nivetude,experience,langue,dateExpiration,tel,email);
        ServiceOffre sc = new ServiceOffre();
        sc.ajouterOffre(c);
                    JOptionPane.showMessageDialog(null, "ajout avec succes");
        txtnomentreprise.clear();
        txtsalaire.clear();
        txtdescription.clear();
        txtlocalisation.clear();
        txtnbrheure.clear();
        txtexperience.clear();
        txtnivetude.clear();
        txtlangue.clear();
        txtemail.clear();
        txttel.clear();
        txtcontrat.clear();
        datepicker.setValue(null);
       
        JavaMailUtil mail = new JavaMailUtil();
           mail.sendMail(c.getEmail());
        
           load();
         }
    }

    @FXML
    private void updateOffre(ActionEvent event) {
        Offre cl=tvoffre.getSelectionModel().getSelectedItem();
              if(cl!=null){
          int id =cl.getIdOffre();
          
         String nomentreprise = txtnomentreprise.getText();
         int sal = Integer.parseInt(txtsalaire.getText());
         String description = txtdescription.getText();
         String localisation = txtlocalisation.getText();
         int nbrheure = Integer.parseInt(txtnbrheure.getText());
         int experience = Integer.parseInt(txtexperience.getText());
         String nivetude = txtnivetude.getText();
         String langue = txtlangue.getText();
         String email = txtemail.getText();
         int tel = Integer.parseInt(txttel.getText());
         Date dateExpiration=java.sql.Date.valueOf(datepicker.getValue());
         String contrat = txtcontrat.getText(); 
         

            
            ServiceOffre sc = new ServiceOffre();
            Offre o = new Offre(id,id_projet.getValue().getId(),nomentreprise,sal,description,localisation,nbrheure,contrat,nivetude,experience,langue,dateExpiration,tel,email);
           //Cours cc = new Cours(id, instru , niveau, vid);
            sc.modifierOffre(o);
            load();
             }
        else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
        }
    }

    @FXML
    private void deleteOffre(ActionEvent event) {
         Offre selectedItem = tvoffre.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        ServiceOffre ps = new ServiceOffre() ; 
        //tvoffre.getItems().remove(selectedItem);
        ps.supprimerOffre(selectedItem);  
        load();
        }
        
        }
        else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
        }
    }
    
     private void load() {
        
            ServiceOffre ps = new ServiceOffre();
        ArrayList<Offre> publiciteList =  (ArrayList<Offre>) ps.getOffre() ;
    ObservableList<Offre> donnee = FXCollections.observableArrayList(publiciteList); 
    tvoffre.setItems(donnee);
        //colid.setCellValueFactory(new PropertyValueFactory<>("IdOffre"));
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
     
     private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if(!testDate())
        {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
          if(!testTel())
        {
            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
        }
           if(!testNom())
        {
            erreur = erreur + ("Veuillez verifier champ nom entreprise vide\n");
        }
               if(!testLocal())
        {
            erreur = erreur + ("Veuillez verifier champ addresse vide\n");
        }
               if(!testdescri())
        {
            erreur = erreur + ("Veuillez verifier champ description vide\n");
        }
               if(!testniv())
        {
            erreur = erreur + ("Veuillez verifier champ niveau etude vide\n");
        }
               if(!testlangue())
        {
            erreur = erreur + ("Veuillez verifier champ langue vide\n");
        }
                if(!testsalaire())
        {
            erreur = erreur + ("Veuillez verifier salaire positif \n");
        }
                if(!testnbr())
        {
            erreur = erreur + ("Veuillez verifier nbr heures positif \n");
        }
                if(!testcontrat())
        {
            erreur = erreur + ("Veuillez verifier champ contrat vide \n");
        }
        if (!testnbr() || !testcontrat() || !testMail() || !testDate() || !testTel() || !testNom() || !testLocal() || !testdescri() || !testlangue() || !testniv() || !testsalaire()) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER);
                    //.hideAfter(Duration.ofSeconds(6));
            n.showInformation();
        }

        return testnbr() && testcontrat() && testMail() && testDate() && testTel() && testNom() && testLocal() && testdescri() && testniv() && testlangue() && testsalaire();
    }
    
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (txtemail.getText() == null) {
            return false;
        }

        if (pat.matcher(txtemail.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Img/checkMark.png"));
        }
        return true;

    }
    
       private Boolean testDate() {
     //   java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
     //String date = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
     Date dateExpiration=java.sql.Date.valueOf(datepicker.getValue());
     java.util.Date aujourdhui = new java.util.Date();
     if (datepicker.getValue() != null) {
            if (dateExpiration.compareTo(aujourdhui) > 0) {
                dateCheckMark.setImage(new Image("Img/checkMark.png"));
                return true;
            } else {
                dateCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
            }
            return false;
        } else {
                return false;
                
               }
        
    }
       
        private Boolean testTel() {
        if (txttel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < txttel.getText().trim().length(); i++) {
                char ch = txttel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("Img/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (txttel.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
            return false;
        }
             return true;

    }
        
         private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < txtnomentreprise.getText().trim().length(); i++) {
            char ch = txtnomentreprise.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtnomentreprise.getText().trim().length() >= 1) {
            nomCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
            private Boolean testLocal() {
        int nbNonChar = 0;
        for (int i = 1; i < txtlocalisation.getText().trim().length(); i++) {
            char ch = txtlocalisation.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtlocalisation.getText().trim().length() >= 1) {
            localCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            localCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
                    private Boolean testdescri() {
        int nbNonChar = 0;
        for (int i = 1; i < txtdescription.getText().trim().length(); i++) {
            char ch = txtdescription.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtdescription.getText().trim().length() >= 1) {
            descCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            descCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
            private Boolean testniv() {
        int nbNonChar = 0;
        for (int i = 1; i < txtnivetude.getText().trim().length(); i++) {
            char ch = txtnivetude.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtnivetude.getText().trim().length() >= 1) {
            expCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            expCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
            private Boolean testlangue() {
        int nbNonChar = 0;
        for (int i = 1; i < txtlangue.getText().trim().length(); i++) {
            char ch = txtlangue.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtlangue.getText().trim().length() >= 1) {
            langueCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            langueCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
                  private Boolean testcontrat() {
        int nbNonChar = 0;
        for (int i = 1; i < txtcontrat.getText().trim().length(); i++) {
            char ch = txtcontrat.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtcontrat.getText().trim().length() >= 1) {
            contratCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            contratCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
            private Boolean testsalaire() {

        if (txtsalaire.getText().trim().length() >= 1 && Integer.parseInt(txtsalaire.getText())>0) {
            salaireCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            salaireCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
            private Boolean testnbr() {

        if (txtnbrheure.getText().trim().length() >= 1 && Integer.parseInt(txtnbrheure.getText())>0) {
            heureCheckMark.setImage(new Image("Img/checkMark.png"));
            return true;
        } else {
            heureCheckMark.setImage(new Image("Img/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    @FXML
    private void stat(MouseEvent event)throws IOException {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Views/stat.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
