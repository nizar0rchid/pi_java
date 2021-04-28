/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nizar
 */
public interface IServiceevent {
public void Addevent(event t) throws SQLException;
public List<event> Afficherevent() throws SQLException;
public void Modifierevent(int id, event t)throws SQLException;
}