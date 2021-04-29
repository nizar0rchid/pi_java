/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagecontroller;

import com.sun.jndi.toolkit.url.Uri;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import packageentity.Cours;
import packageservice.coursService;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class HomeController implements Initializable {

    @FXML
    private FlowPane fp;
    private ArrayList<ImageView> img=new ArrayList<>();
    private ArrayList<Hyperlink> hp=new ArrayList<>();
    public ArrayList<Label>title=new ArrayList<>();
     public ArrayList<Image>arl=new ArrayList<>();
     public ArrayList<Label>pnl=new ArrayList<>();
        private ArrayList<Cours> cours=new ArrayList<>();
        private ArrayList<String> xx=new ArrayList<>();
@FXML
    private TextField search;

    private  ListData listdata = new ListData();
    /**
     * Initializes the controller class.
     */
        
        @FXML
    void search() {
        FilteredList<Cours> filteredData = new FilteredList<>(listdata.getCours(), e -> true);
        search.setOnKeyReleased(event -> {
        search.textProperty().addListener(( observableValue,oldValue,newValue) -> {
            filteredData.setPredicate((Predicate <? super Cours>) cours -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (cours.getTitle().toLowerCase().contains(newValue)) {
                    return true;
                
                } else if (cours.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
            
              
            } else if (cours.getUrl().toLowerCase().contains(lowerCaseFilter)) {
                    return true; }
            else if (cours.getImage().toLowerCase().contains(lowerCaseFilter)) {
                    return true; }
              return false;      
        });
    });
        SortedList<Cours> sortedData = new SortedList<>(filteredData);
        /*sortedData.comparatorProperty().bind(tvCours.comparatorProperty());
        tvCours.setItems(sortedData);*/
        
        

    });

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          coursService pdao =new coursService();
                   
                   for(Cours c : pdao.displayAllList()){
                   title.add(new Label(c.getTitle()));
                   arl.add(new Image(c.getImage()));
                   hp.add(new Hyperlink(c.getUrl()));
                   xx.add(c.getUrl());
                   
                   pnl.add(new Label("By : Mr "+c.getAuthor()));
                   }
                   final WebView wb = new WebView();
                            
                  
                          
                  
                   for (int i=0; i<title.size(); i++){
                        img.add(new ImageView(arl.get(i)));
                        img.get(i).setFitWidth(250);
                        img.get(i).setFitHeight(180);
                        
                        title.get(i).setWrapText(true);
                        title.get(i).setMaxWidth(150);
                        title.get(i).setTextFill(Color.WHITE);
                        
                        pnl.get(i).setWrapText(true);
                        pnl.get(i).setMaxWidth(150);
                        pnl.get(i).setTextFill(Color.WHITE);
                        
                         String x= xx.get(i);
        
                        
                    
                   
                  
                       hp.get(i).setOnAction(e->{
                           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/packageview/webview.fxml"));
                Region root = (Region) loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                WebviewController spc = loader.getController();
                spc.setIdd(x);//envoie de l'ID de la photo   
                stage.setScene(scene);
                stage.show();}
    catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                        });}
                     for (int i=0; i<title.size(); i++){
                         BorderPane bp = new BorderPane();
                         VBox vb= new VBox();
                         vb.getChildren().addAll(title.get(i),pnl.get(i));
                         vb.setAlignment(Pos.CENTER_RIGHT);
                         vb.setSpacing(20);
                         
                       bp.setLeft(img.get(i));
                       bp.setCenter(vb);
                      
                       bp.setBottom(hp.get(i));
                       //bp.setRight(pnl.get(i));
                       bp.setPrefSize(400, 200);
                       
                        BorderPane.setAlignment(img.get(i), Pos.CENTER);
                        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                       fp.getChildren().addAll(bp);
                   
                    
                       
                        
                    }
                   
                       
                       
                    
                
                      
                      
                   
    }}           

        
        
    

