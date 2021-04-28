/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.sql.Date;

/**
 *
 * @author Nizar
 */
public class event {
    int id;
    String nom;
    String lieu;
    Date date;
    int capacite;
    String description;
    int capaciteinitiale;

    public event() {
    }

    public event(int id, String nom, String lieu, Date date, int capacite, String description) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapaciteinitiale() {
        return capaciteinitiale;
    }

    public void setCapaciteinitiale(int capaciteinitiale) {
        this.capaciteinitiale = capaciteinitiale;
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", capacite=" + capacite + ", description=" + description + ", capaciteinitiale=" + capaciteinitiale + '}';
    }

    public event(int id, String nom, String lieu, Date date, int capacite, String description, int capaciteinitiale) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.capacite = capacite;
        this.description = description;
        this.capaciteinitiale = capaciteinitiale;
    }

    
    
    
}
