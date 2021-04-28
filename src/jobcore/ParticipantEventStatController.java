/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcore;

import Entity.event;
import tools.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class ParticipantEventStatController implements Initializable {

    @FXML
    private AnchorPane filsdash;
    @FXML
    private Button eventuser;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField tfid;
    @FXML
    private BarChart<?, ?> chart;
    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private CategoryAxis CategoryAxis;

    ObservableList<event> eventList = FXCollections.observableArrayList();
    List<Integer> participantsList = new ArrayList<Integer>();
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button go;
    @FXML
    private Button go1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        countevent();
  
    }

@FXML
    private void countevent() {
        try {
            Connection cnx = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM event";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            event event;
            eventList.clear();
            participantsList.clear();
            chart.getData().clear();
            
            while (rs.next()) {
                event = new event(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"), rs.getDate("date"), rs.getInt("capacite"), rs.getString("description"), rs.getInt("capaciteinitiale"));
                eventList.add(event);
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        for (event e : eventList){
            int nbrparticipants = e.getCapaciteinitiale()-e.getCapacite();
            participantsList.add(nbrparticipants);
        }
        XYChart.Series setl = new XYChart.Series<>();
         for (int i = 0; i < eventList.size(); i++){
             setl.getData().add(new XYChart.Data(eventList.get(i).getNom(),participantsList.get(i)));
         }
        
            
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
         
        chart.getData().addAll(setl);
        
    }
    @FXML
    private void BouttonAdmin(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) eventuser.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EventAdmin.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void counteventbydate() {
        try {
            Connection cnx = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM event WHERE date BETWEEN "+"'"+Date.valueOf(datedebut.getValue())+"'"+" AND "+"'"+Date.valueOf(datefin.getValue())+"'"+"";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            event event;
            eventList.clear();
            participantsList.clear();
            chart.getData().clear();
            
            while (rs.next()) {
                event = new event(rs.getInt("id"), rs.getString("nom"), rs.getString("lieu"), rs.getDate("date"), rs.getInt("capacite"), rs.getString("description"), rs.getInt("capaciteinitiale"));
                eventList.add(event);
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        for (event e : eventList){
            int nbrparticipants = e.getCapaciteinitiale()-e.getCapacite();
            participantsList.add(nbrparticipants);
        }
        
        XYChart.Series setl = new XYChart.Series<>();
         for (int i = 0; i < eventList.size(); i++){
             setl.getData().add(new XYChart.Data(eventList.get(i).getNom(),participantsList.get(i)));
         }
        
            
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
         
        chart.getData().addAll(setl);
        
        
    }

    
    
    
    
}
