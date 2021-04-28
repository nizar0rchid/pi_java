/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.reservation_event;
import Entity.event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tools.Connexion;

/**
 *
 * @author Nizar
 */
public class ServiceReservationEvent {
    Connection cnx;
    private List<reservation_event> event;
    private PreparedStatement pre;
    private Statement ste;
    
    
    public ServiceReservationEvent() {
    cnx =Connexion.getInstance().getConnection();
    }
    
   
}
