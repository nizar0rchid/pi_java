/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcore;

import Entity.event;
import Service.Serviceevent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class EventUser2Controller implements Initializable {
    private List<event> events = new ArrayList<>();

    @FXML
    private Button gestionreservation;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField tfidevent;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showProducts();
        } catch (SQLException ex) {
            Logger.getLogger(EventUser2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventUser2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

   public void showProducts() throws SQLException, IOException{
       
        grid.getChildren().clear();
        events.clear();
        Serviceevent sp = new Serviceevent();

        events = sp.read();
        int column = 0;
        int row = 1;
        //local produits
        int i = 0;
       
    

        try {
            for (i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SingleEvent.fxml"));
                AnchorPane singleevent = fxmlLoader.load();

                SingleEventController singleventcontroller = fxmlLoader.getController();
                singleventcontroller.setData(events.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(singleevent, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_PREF_SIZE);
                grid.setPrefWidth(997);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(singleevent, new Insets(20));
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   
    private void show(ActionEvent event) throws SQLException, IOException {
        showProducts();
    }
    @FXML
    private void Bouttonreservation(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) gestionreservation.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReservationEventUser.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
