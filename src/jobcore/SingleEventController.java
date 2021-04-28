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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import tools.Connexion;
import tools.JavaMail;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;




/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class SingleEventController implements Initializable {

    @FXML
    private Label nomevent;
    @FXML
    private Label lieuevent;
    @FXML
    private Label dateevent;
    @FXML
    private Label descp;
    @FXML
    private Label descriptionevent;
    private event event;
    @FXML
    private Button reserver;
    @FXML
    private TextField idevent;
    
    String temperature;
    
    Label b = new Label();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        /*reserver.hoverProperty().addListener(l->{
        System.out.println("Button is being hovered");
    });*/
        
        Stage stickyNotesStage = new Stage();
        
        StackPane stickyNotesPane = new StackPane();
        stickyNotesPane.setPrefSize(200, 200);
        stickyNotesPane.setStyle("-fx-background-color: white;");
        //Label b = new Label("test");
        stickyNotesPane.getChildren().add(b);

        Popup popup = new Popup();
        popup.getContent().add(stickyNotesPane);

        reserver.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                Bounds bnds = reserver.localToScreen(reserver.getLayoutBounds());
                double x = bnds.getMinX() - (stickyNotesPane.getWidth() / 2) + (reserver.getWidth() / 2);
                double y = bnds.getMinY() - stickyNotesPane.getHeight();
                popup.show(reserver, x, y);
            } else {
                popup.hide();
            }
        });
        
        
        
        
        
        
    }
    
    public static Map<String, Object> jsonToMap(String str) {
        
         Map<String, Object> map = new Gson().fromJson(
                 str, new TypeToken<HashMap<String, Object>>() {}.getType()
         );
         return map;
        }
        

    

    
    //private MyListener myListener;

    public void setData(event event) {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String DateEventString  = df.format(event.getDate());
        
        this.event= event;
        
        idevent.setText(String.valueOf(event.getId()));
        nomevent.setText(event.getNom());
        lieuevent.setText(event.getLieu());
        dateevent.setText(DateEventString);
        descriptionevent.setText(event.getDescription());
        weather();
      
        
        
        
    }
    
    
    public void weather(){
        
        String API_KEY = "c7227649904ca2f167086449a1dac3ab";
        //String LOCATION = "Ariana,TN";
        String LOCATION = event.getLieu()+",TN";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+API_KEY+"&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL link = new URL(urlString);
            URLConnection conn = link.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result);
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
            
            //temperature = "Current temperature : "+mainMap.get("temp");
            temperature = "Current temperature: "+mainMap.get("temp")+"\n Current Humidity : "+mainMap.get("humidity")+"\n Wind speed : "+windMap.get("speed")+"\n Wind Angle : "+windMap.get("deg")+"";
            b.setText(temperature);
            
            System.out.println("Current temperature : "+mainMap.get("temp"));
            System.out.println("Current Humidity : "+mainMap.get("humidity"));
            System.out.println("Wind speed : "+windMap.get("speed"));
            System.out.println("Wind Angle : "+windMap.get("deg"));
            
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        
    }
    

    @FXML
    private void click(MouseEvent event) {
    }
    @FXML
    private void reserver(ActionEvent event) throws SQLException, Exception {
        ServiceReservationEvent aa = new ServiceReservationEvent();

        Scanner sc = new Scanner(System.in);
        Connection cnx = Connexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        ResultSet mail;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();

        reservation_event t = new reservation_event();
        String SQL = "SELECT * FROM reservation_event WHERE idevent = " + idevent.getText() + " AND idclient=1"; ///
        rs = stm.executeQuery(SQL);
        String check = "SELECT capacite FROM event WHERE id = " + idevent.getText() + "";
        PreparedStatement checkstm1 = cnx.prepareStatement(check);
        ResultSet checkcapacite = checkstm1.executeQuery(check);
        Alert confirmation = new Alert(AlertType.CONFIRMATION, "Voulez vous reserver à " + nomevent.getText() + " ?", ButtonType.YES, ButtonType.CANCEL);
        confirmation.setHeaderText(null);
        confirmation.showAndWait();
        if (confirmation.getResult() == ButtonType.YES) {
            if (checkcapacite.next()) {
                int capacite = checkcapacite.getInt("capacite");

                if (capacite > 0) {

                    String query = "UPDATE event SET capacite = " + (capacite - 1) + " WHERE id = " + idevent.getText() + "";
                    PreparedStatement update = cnx.prepareStatement(query);
                    int i = update.executeUpdate();

                    if (!rs.next()) {
                        String req = "INSERT INTO reservation_event (idevent,idclient) values (?,?)";
                        try {

                            PreparedStatement stm1 = cnx.prepareStatement(req);
                            stm1.setInt(1, Integer.parseInt(idevent.getText()));
                            stm1.setInt(2, 1); // passage statique de id = 1 du user

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Reservation effectuée avec succes\n Vous recevrez un email dans les plus bref delais.");
                            alert.showAndWait();
                            stm1.executeUpdate();
                            String mailquery = "SELECT email FROM user WHERE id = 1"; // remplacer 1 avec le user connecter
                            PreparedStatement stm1l = cnx.prepareStatement(mailquery);
                            mail = stm1l.executeQuery(mailquery);
                            if (mail.next()) {
                                String Email = mail.getString("email");
                                // System.err.println(Email); // debug

                                JavaMail.sendMail(Email);
                                //showevent2();
                            }

                            System.out.println("Reservation ajouté");
                        } catch (SQLException ex) {
                            System.out.println("probleme");
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Vous avez deja reserver à cet evenement");
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("l'evenement est deja au complet");
                    alert.showAndWait();
                }
            }
        }
        
    }
    
    
}
