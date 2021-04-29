/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insc;



import packageentity.inscription;
import java.awt.Desktop;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import packagecontroller.ListData;
import packageentity.Cours;
import packageservice.InscriService;

/**
 * FXML Controller class
 *
 * @author ali
 */
public class InscriptionController implements Initializable {
    
    InscriService cr = new InscriService();
    ObservableList<inscription> data = FXCollections.observableArrayList(cr.getAll());;

    private TableView<inscription> table;
    private TableColumn<inscription, String> colNom;
    private TableColumn<inscription, String> colEmail;
    private TextField Nom;
    private TextField Email;
    private TextField recherche;
    private TableView<Cours> tvCours;
    private TableColumn<Cours, String> colTitle;
    private TableColumn<Cours, String> colAuthor;
    private TableColumn<Cours, String> colcategory;
    private TableColumn<Cours, String> coldate;
    private TableColumn<Cours, String> colimage;
    private TableColumn<Cours, String> colfile;
    private  ListData listdata = new ListData();
    private final Desktop desktop = Desktop.getDesktop();
    private ObservableList<Cours> list=FXCollections.observableArrayList();
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNom.setCellValueFactory(new PropertyValueFactory("cour_nom"));
         colEmail.setCellValueFactory(new PropertyValueFactory("cour_email"));
         table.setItems(data);
         table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    packageentity.inscription e = (packageentity.inscription) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      Nom.setText(e.getCour_nom());
                      Email.setText(e.getCour_email());}
                      
            }
        });
           recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               filtrerEventList((String) oldValue, (String) newValue);
            }

        }); 
          
           tvCours.setItems(listdata.getCours());
       
        colTitle.setCellValueFactory(cell -> cell.
                getValue().getTitleProperty());
        colAuthor.setCellValueFactory(cell -> cell.
                getValue().getAuthorProperty());
        colcategory.setCellValueFactory(cell -> cell.
                getValue().getCategoryProperty());
       
        coldate.setCellValueFactory(cell -> cell.
                getValue().getDateProperty());
        
        colfile.setCellValueFactory(cell -> cell.
                getValue().getUrlProperty());
        colimage.setCellValueFactory(cell -> cell.
                getValue().getImageProperty());
    } 
    
    
    void filtrerEventList(String oldValue, String newValue) {
        InscriService evs = new InscriService();
        ObservableList<inscription> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<inscription>) evs.FindEvent());
        } else {
            table.setItems((ObservableList<inscription>) evs.FindEvent());
            newValue = newValue.toUpperCase();

            for (inscription e : table.getItems()) {

                String filterEventName = e.getCour_nom();
               

                if (filterEventName.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }


    private void ajout(ActionEvent event ) throws Exception  {
        
         inscription e = new inscription(Nom.getText(), Email.getText());
       System.out.println(e.toString());
        cr.createEvenement(e);
       
     data.removeAll(data);
         for (inscription ev : FXCollections.observableArrayList(cr.getAll())) {
            data.add(ev);

        }
         clear();
         //Notification

         Notifications notf =Notifications.create()
                                           .title("Votre inscription est Accepté")
                                            .graphic(null)
                                             .hideAfter(Duration.seconds(5))
                                              .position(Pos.TOP_RIGHT)
                                               .onAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.out.println("Clicker sur Notification");
             }
                                               });
         notf.showInformation();
         
         //Email
         JavaMailUtil mail = new JavaMailUtil();
           mail.sendMail(e.getCour_email());
         
    }
    
 
    
    
    
    
    
    private void clear() {
        table.getSelectionModel().clearSelection();
       Nom.clear();
        Email.clear();
    }

    private void Modif(ActionEvent event) {
        
                 if (table.getSelectionModel().getSelectedItem() != null) {
            cr.update(new packageentity.inscription(Nom.getText(), Email.getText()), table.getSelectionModel().getSelectedItem().getId());
            System.out.println();
            data.removeAll(data);
            for (inscription e : FXCollections.observableArrayList(cr.getAll())) {
                data.add(e);
            }
            clear();
        }
    }

    private void Clear(ActionEvent event) {
        clear();
    }

    private void Delete(ActionEvent event) {
        
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous Annuler ton Inscription !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Inscription Annuler");
                cr.delete(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (inscription e : FXCollections.observableArrayList(cr.getAll())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }
    }
    
}
