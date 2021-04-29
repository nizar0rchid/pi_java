/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagecontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import packageentity.Cours;
import packageservice.coursService;



/**
 *
 * @author Amine
 */
public class ListData {
    
     /**
     * The data as an observable list of Cours.
     */
    
    private ObservableList<Cours> cours=FXCollections.observableArrayList();

    public ListData() {
        
        coursService pdao=coursService.getInstance();
        cours= pdao.displayAll();
        System.out.println(cours);
    }
    
    public ObservableList<Cours> getCours(){
        return cours;
    }
    
   
}
