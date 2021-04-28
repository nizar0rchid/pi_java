/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcore;

import Entity.event;
import Entity.User;
import Entity.reservation_event;
import Service.ServiceReservationEvent;
import Service.Serviceevent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tools.Connexion;
import tools.JavaMail;

/**
 * FXML Controller class
 *
 * @author Nizar
 */
public class EventUserController implements Initializable {

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
    private TextField rechercheuser;
    @FXML
    private ChoiceBox<String> choiceuser;
    @FXML
    private Button eventadmin;
    @FXML
    private TextField tfidevent;
    @FXML
    private Button gestionreservation;

    Serviceevent cr = new Serviceevent();
    ObservableList<event> data = FXCollections.observableArrayList(cr.getAll());
    ;
    
    
    /**
     * Initializes the controller class.
     */
    ObservableList<event> eventList = FXCollections.observableArrayList();
    ObservableList<event> eventList2;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button reserver;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showevent();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Entity.event e = (Entity.event) table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    tfidevent.setText(String.valueOf(e.getId()));

                }
            }
        });

        FilteredList<event> flevent;//Pass the data to a filtered list
        flevent = new FilteredList(data, e -> true);
        table.setItems(flevent);//Set the table's items using the filtered list
        choiceuser.getItems().addAll("Nom", "Lieu");
        choiceuser.setValue("Nom");

        rechercheuser.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceuser.getValue())//Switch on choiceBox value
            {
                case "Nom":
                    flevent.setPredicate(e -> e.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Lieu":
                    flevent.setPredicate(e -> e.getLieu().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;

            }
        });

        choiceuser.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                rechercheuser.setText("");
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
    private void BouttonAdmin(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) eventadmin.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EventAdmin.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        String SQL = "SELECT * FROM reservation_event WHERE idevent = " + tfidevent.getText() + " AND idclient=1"; ///
        rs = stm.executeQuery(SQL);
        String check = "SELECT capacite FROM event WHERE id = " + tfidevent.getText() + "";
        PreparedStatement checkstm1 = cnx.prepareStatement(check);
        ResultSet checkcapacite = checkstm1.executeQuery(check);
        if (checkcapacite.next()) {
            int capacite = checkcapacite.getInt("capacite");

            if (capacite > 0) {

                String query = "UPDATE event SET capacite = " + (capacite - 1) + " WHERE id = " + tfidevent.getText() + "";
                PreparedStatement update = cnx.prepareStatement(query);
                int i = update.executeUpdate();

                if (!rs.next()) {
                    String req = "INSERT INTO reservation_event (idevent,idclient) values (?,?)";
                    try {

                        PreparedStatement stm1 = cnx.prepareStatement(req);
                        stm1.setInt(1, Integer.parseInt(tfidevent.getText()));
                        stm1.setInt(2, 1); // passage statique de id = 1 du user

                        Alert alert = new Alert(AlertType.INFORMATION);
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
                            showevent2();
                        }

                        System.out.println("Reservation ajouté");
                    } catch (SQLException ex) {
                        System.out.println("probleme");
                        System.out.println(ex.getMessage());
                    }
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous avez deja reserver à cet evenement");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("l'evenement est deja au complet");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void Bouttonreservation(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) eventadmin.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ReservationEventUser.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
