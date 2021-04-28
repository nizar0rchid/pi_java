/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Categorie;
import Services.ServiceCategorie;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField txtcategorie;
    @FXML
    private Button btnajout;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TableView<Categorie> tv;
    @FXML
    private TableColumn<Categorie, Integer> colid;
    @FXML
    private TableColumn<Categorie, String> colcategorie;
    @FXML
    private TableColumn<Categorie, String> colcouleur;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModifier;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] possibleWords = {"commerce","informatique","comptabilite"};
        TextFields.bindAutoCompletion(txtcategorie, possibleWords);
        
        
         ServiceCategorie ps = new ServiceCategorie();
        ArrayList<Categorie> publiciteList =  (ArrayList<Categorie>) ps.getCategories() ;
    ObservableList<Categorie> data = FXCollections.observableArrayList(publiciteList); 
    tv.setItems(data);
        //colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        colcouleur.setCellValueFactory(new PropertyValueFactory<>("color"));
       
        /* tv.setEditable(true);
        colid.setCellFactory(TextFieldTableCell.forTableColumn());
        colcategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        colcouleur.setCellFactory(TextFieldTableCell.forTableColumn()); */
    }    

    @FXML
    private void addCategorie(ActionEvent event) {
        if(txtcategorie.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "verifer les champs");  
        }
        else
        {
        String nom_categorie = txtcategorie.getText();
         String color = Integer.toHexString(colorPicker.getValue().hashCode());
        Categorie c = new Categorie(5,nom_categorie,color);
        ServiceCategorie sc = new ServiceCategorie();
        sc.ajouterCategorie(c);
                    //JOptionPane.showMessageDialog(null, "ajout avec succes");
                      Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Categorie ajoutée avec succes")
                            .position(Pos.BOTTOM_RIGHT)
                            ;
                    notificationBuilder.show();
        txtcategorie.clear();
       
         ArrayList<Categorie> publiciteList =  (ArrayList<Categorie>) sc.getCategories() ; 
    ObservableList<Categorie> data = FXCollections.observableArrayList(publiciteList);
    
    tv.setItems(data);
        //colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        colcouleur.setCellValueFactory(new PropertyValueFactory<>("color"));
         }
    }

    @FXML
    private void deleteCategorie(ActionEvent event) {
        
          Categorie selectedItem = tv.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Etes vous sure de supprimer cette element ?");   
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        ServiceCategorie ps = new ServiceCategorie() ; 
        tv.getItems().remove(selectedItem);
        ps.supprimerCategorie(selectedItem);
          Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("Offre supprimée avec succes")
                            .position(Pos.BOTTOM_RIGHT)
                            ;
                    notificationBuilder.show();
        }
        
        }
        else {
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un element à supprimer.");

        alert.showAndWait();
        }
        
    }

    @FXML
    private void updateCategorie(ActionEvent event) {
    
        Categorie cl=tv.getSelectionModel().getSelectedItem();
          int id =cl.getId();
           String noms=txtcategorie.getText();
           String color = Integer.toHexString(colorPicker.getValue().hashCode());

            
            ServiceCategorie sc = new ServiceCategorie();
            Categorie o = new Categorie(id, noms, color);
           //Cours cc = new Cours(id, instru , niveau, vid);
            sc.modifierCategorie(o);
            ArrayList<Categorie> publiciteList =  (ArrayList<Categorie>) sc.getCategories() ;
    ObservableList<Categorie> data = FXCollections.observableArrayList(publiciteList); 
    tv.setItems(data);
        //colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        colcouleur.setCellValueFactory(new PropertyValueFactory<>("color"));
        /* Categorie p = tv.getSelectionModel().getSelectedItem();
           txtcategorie.setText( p.getNom_categorie());
          // passwordField.setText( p.getColor());
        ServiceCategorie ps = new ServiceCategorie();
        ps.modifierCategorie(p, p.getId());*/
        
          // selectedRegisterModel = getTableView().getItems().get(getIndex());
                                  
    }
    
}
