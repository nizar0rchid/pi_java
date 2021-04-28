/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root=FXMLLoader.load(getClass().getResource("AnnonceFront.fxml"));
            Parent root=FXMLLoader.load(getClass().getResource("AjouterOffre.fxml"));
            //Parent root=FXMLLoader.load(getClass().getResource("AdminOffre.fxml"));
            //Parent root=FXMLLoader.load(getClass().getResource("AjouterAffectation.fxml"));
            //Parent root=FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));
            //Parent root=FXMLLoader.load(getClass().getResource("AdminCategorie.fxml"));
            /*Button btn = new Button();
            btn.setText("Say 'Hello World'");
            btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Hello World!");
            }
            });
            
            StackPane root = new StackPane();
            root.getChildren().add(btn);*/
            
            Scene scene = new Scene(root, 1138, 585);
            
            primaryStage.setTitle("Gestion Offres");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
