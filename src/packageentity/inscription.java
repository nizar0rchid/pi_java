/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageentity;

/**
 *
 * @author ali
 */
public class inscription {
    private int id;
    private String cour_nom,cour_email ;
    
    public inscription(){}

    public inscription(int id, String cour_nom, String cour_email) {
        this.id = id;
        this.cour_nom = cour_nom;
        this.cour_email = cour_email;
    }

    public inscription(String cour_nom, String cour_email) {
         //To change body of generated methods, choose Tools | Templates.
         this.cour_nom = cour_nom;
        this.cour_email = cour_email;
    }

    @Override
    public String toString() {
        return "inscription{" + "id=" + id + ", cour_nom=" + cour_nom + ", cour_email=" + cour_email + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCour_nom() {
        return cour_nom;
    }

    public void setCour_nom(String cour_nom) {
        this.cour_nom = cour_nom;
    }

    public String getCour_email() {
        return cour_email;
    }

    public void setCour_email(String cour_email) {
        this.cour_email = cour_email;
    }
}
