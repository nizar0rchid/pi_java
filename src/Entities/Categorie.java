/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Categorie {
    
    private int id;
    private String nom_categorie,color;
    
    public Categorie(){
    }
    
    public Categorie(int id, String nom_categorie, String color ) {
        this.id = id;
        this.nom_categorie = nom_categorie;
        this.color = color;   
    }
    
    public Categorie( String nom_categorie, String color ) {
        this.nom_categorie = nom_categorie;
        this.color = color;   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_categorie=" + nom_categorie + ", color=" + color + '}';
    }
    
    
    
}
