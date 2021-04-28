/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Nizar
 */
public class reservation_event {
    int id;
    int idevent;
    int idclient;

    public reservation_event() {
    }

    public reservation_event(int id, int idevent, int idclient) {
        this.id = id;
        this.idevent = idevent;
        this.idclient = idclient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Override
    public String toString() {
        return "reservation_event{" + "id=" + id + ", idevent=" + idevent + ", idclient=" + idclient + '}';
    }
    
    
}
