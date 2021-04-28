/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcore;

import Entity.event;
import Entity.reservation_event;
import Service.ServiceReservationEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class ReservationEventUserController implements Initializable {

    @FXML
    private Button gestionreservation;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<event> table;
    @FXML
    private TableColumn<event, Integer> idevent;
    @FXML
    private TableColumn<event, String> nomevent;
    @FXML
    private TableColumn<event, Date> dateevent;
    @FXML
    private TableColumn<event, String> lieuevent;
    @FXML
    private TableColumn<event, Integer> capaciteevent;
    @FXML
    private TableColumn<event, String> descriptionevent;
    @FXML
    private TextField tfid;
    @FXML
    private Button annuler;
    @FXML
    private TextField idreser;
    @FXML
    private Button listeevent;

    ObservableList<event> eventList = FXCollections.observableArrayList();
    ObservableList<event> eventList2;
    ObservableList<reservation_event> reseventList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showreservation();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Entity.event e = (Entity.event) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                     idreser.setText(String.valueOf(e.getId()));
                     

                }
            }
        });
    }    
    
    
    
    
    
         @FXML
        public void showreservation() {
            try {
                Connection cnx = Connexion.getInstance().getConnection();
                String query = "SELECT * FROM event WHERE id IN (SELECT idevent FROM reservation_event)";
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
        
        
        
      @FXML
    private void annulerreservation (ActionEvent event) throws SQLException, Exception {
        ServiceReservationEvent aa = new ServiceReservationEvent();
        
        Scanner sc = new Scanner(System.in);
        Connection cnx = Connexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        
        
         reservation_event t = new reservation_event();
         String SQL = "SELECT * FROM reservation_event WHERE idevent = "+idreser.getText()+" AND idclient=1"; ///
         rs = stm.executeQuery(SQL);
           
            String req ="DELETE FROM reservation_event WHERE idevent = ? AND idclient = 1";  // remplacer par ? when user
            try {

               PreparedStatement stm1 = cnx.prepareStatement(req);
                stm1.setInt(1, Integer.parseInt(idreser.getText()));
                int i=stm1.executeUpdate();
              //  stm1.setInt(2, 1); // passage statique de id = 1 du user

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Reservation annulée avec succes");
                alert.showAndWait();
                stm1.executeUpdate();
                String check = "SELECT capacite FROM event WHERE id = "+idreser.getText()+"";
                PreparedStatement checkstm1 = cnx.prepareStatement(check);
                ResultSet checkcapacite = checkstm1.executeQuery(check);
                if (checkcapacite.next()) {
                int capacite = checkcapacite.getInt("capacite");
                String query = "UPDATE event SET capacite = " + (capacite + 1) + " WHERE id = "+idreser.getText()+"";
                PreparedStatement update = cnx.prepareStatement(query);
                int j = update.executeUpdate();
                }

                System.out.println("Reservation annulée");
            }

                     
         catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        } 
            
         showreservationevent2();
         
    }   
    
    @FXML
    public void showreservationevent2() {
          eventList.removeAll(eventList);
        try {
            Connection cnx = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM event WHERE id IN (SELECT idevent FROM reservation_event)";
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
    private void Bouttonevent (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
       
       
            stage = (Stage) listeevent.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("EventUser2.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
