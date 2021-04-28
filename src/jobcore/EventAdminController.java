/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcore;

import Entity.event;
import Service.Serviceevent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tools.Connexion;


/**
 *
 * @author Nizar
 */
public class EventAdminController implements Initializable {
    @FXML
    private DatePicker tfdate;
    private Label label;
    @FXML
    private AnchorPane filsdash;
    @FXML
    private Button gestevent;
    @FXML
    private Button eventuser;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<event> table;
    
    @FXML
    private TableColumn<event, Integer> idevent;
    @FXML
    private TableColumn<event, String> lieuevent;
    @FXML
    private TableColumn<event, Integer> capaciteevent;
    @FXML
    private TableColumn<event, Date> dateevent;
    @FXML
    private Button supprimertemoin;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tflieu;
    @FXML
    private Button ajoutertemoin;
    @FXML
    private Button modifiertemoin;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcapacite;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<event, String> nomevent;
    @FXML
    private TableColumn<event, String> descriptionevent;
    @FXML
    private ChoiceBox<String> choice;
    @FXML
    private Button reservstat;
    
    Serviceevent cr = new Serviceevent();
    ObservableList<event> data = FXCollections.observableArrayList(cr.getAll());;
    
    
    /**
     * Initializes the controller class.
     */
    ObservableList<event> eventList = FXCollections.observableArrayList();
    ObservableList<event> eventList2;
    
    
    
    @FXML
    private void BouttonUser (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
       
            stage = (Stage) eventuser.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("EventUser2.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showevent();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Entity.event r = (Entity.event) table.getSelectionModel().getSelectedItem();
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Entity.event e = (Entity.event) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                     tfid.setText(String.valueOf(e.getId()));
                     tfdate.setValue(e.getDate().toLocalDate());
                      tftitre.setText(e.getNom());
                      tflieu.setText(e.getLieu());
                      tfdescription.setText(e.getDescription());
                      tfcapacite.setText(Integer.toString(e.getCapacite()));

                }
            }
            
        });
        
        FilteredList<event> flevent;//Pass the data to a filtered list
        flevent = new FilteredList(data, e -> true);
        table.setItems(flevent);//Set the table's items using the filtered list
        choice.getItems().addAll("Nom", "Lieu");
        choice.setValue("Nom");
        
        recherche.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choice.getValue())//Switch on choiceBox value
            {
                case "Nom":
                    flevent.setPredicate(e -> e.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Lieu":
                    flevent.setPredicate(e -> e.getLieu().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
                
            }
        });

        choice.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                recherche.setText("");
            }
        });
        
    }  

    public void showevent() {
        try {
            Connection cnx = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM event";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            event event;
            while (rs.next()) {
                event = new event(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"), rs.getDate("date"), rs.getInt("capacite"), rs.getString("description"));
                eventList.add(event);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomevent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieuevent.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        dateevent.setCellValueFactory(new PropertyValueFactory<>("date"));
        capaciteevent.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        descriptionevent.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setItems(eventList);
    }
    public void showevent2() {
          eventList.removeAll(eventList);
        try {
            Connection cnx = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM event";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            event event;
            while (rs.next()) {
                event = new event(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"), rs.getDate("date"), rs.getInt("capacite"), rs.getString("description"));
                eventList.add(event);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomevent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieuevent.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        dateevent.setCellValueFactory(new PropertyValueFactory<>("date"));
        capaciteevent.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        descriptionevent.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setItems(eventList);;
    }
      

    @FXML
    private void supprimerevent(ActionEvent event) {
         eventList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = Connexion.getInstance().getConnection();
            int id;
            id=eventList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from event where id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
                       tfid.clear();
                       tftitre.clear();
                       tflieu.clear();
                       tfdate.getEditor().clear();
                       tfcapacite.clear();
                       tfdescription.clear();
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showevent2();
    }

    @FXML
    private void ajoutevent(ActionEvent event) throws IOException, SQLException {
        
        Serviceevent aa = new Serviceevent();
        Scanner sc = new Scanner(System.in);
        Connection cnx = Connexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        
        
         event t = new event();
        
            t.setNom(tftitre.getText());
            t.setLieu(tflieu.getText());
            t.setDate(Date.valueOf(tfdate.getValue()));
            t.setCapacite(Integer.parseInt(tfcapacite.getText()));
            t.setCapaciteinitiale(Integer.parseInt(tfcapacite.getText()));
            t.setDescription(tfdescription.getText());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();
            // Date now = new Date();
            LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tftitre.getText());
            boolean nom = m.find();
            if(
                    tftitre.getText().isEmpty() || tflieu.getText().isEmpty() || tfdescription.getText().isEmpty() || tfcapacite.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            
            else if (nom){
                   Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le nom ne doit pas contenir des symboles");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfcapacite.getText()) == 0
                       ) { 
               Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("La capacité de l'evenment ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfcapacite.getText()) < 0
                       ) { 
               Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("La capacité de l'evenment ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
               else if (
                        tfdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
               
               
               else {
                    aa.Addevent(t); 
                       showevent2(); 
                       tftitre.clear();
                       tflieu.clear();
                       tfdate.getEditor().clear();
                       tfcapacite.clear();
                       tfdescription.clear();
                   
               }
 }                  


    @FXML
    private void modifierevent(ActionEvent event) throws SQLException {
        Serviceevent sr = new Serviceevent();
        event t = new event();
        LocalDate ld = LocalDate.now(ZoneId.of("Europe/Paris"));
        int id;
        id = Integer.parseInt(tfid.getText());
            t.setNom(tftitre.getText());
            t.setLieu(tflieu.getText());
            t.setDate(Date.valueOf(tfdate.getValue()));
            t.setCapacite(Integer.parseInt(tfcapacite.getText()));
            t.setCapaciteinitiale(Integer.parseInt(tfcapacite.getText()));
            
            t.setDescription(tfdescription.getText());
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(tftitre.getText());
            boolean nom = m.find();
            if(
                    tftitre.getText().isEmpty() || tflieu.getText().isEmpty() || tfdescription.getText().isEmpty() || tfcapacite.getText().isEmpty()
                    ) 
            {
                Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir le formulaire");
                    alert.showAndWait();
            }
            else if (nom){
                   Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Le nom ne doit pas contenir des symboles");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfcapacite.getText()) == 0
                       ) { 
               Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("La capacité de l'evenment ne peut pas etre nulle");
                    alert.showAndWait();
               }
               else if(
                       Integer.parseInt(tfcapacite.getText()) < 0
                       ) { 
               Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("La capacité de l'evenment ne peut pas inferieure à 0");
                    alert.showAndWait();
               }
               else if (
                        tfdate.getValue().isBefore(ld)
                       )
               {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Alerte !");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez inserer une date valide");
                    alert.showAndWait();
               }
        
               else { sr.Modifierevent(id, t); }
        showevent2();
        

    }
    
   
    @FXML
    private void stat (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
       
            stage = (Stage) eventuser.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ParticipantEventStat.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
