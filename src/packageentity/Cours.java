/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageentity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Amine
 */
public class  Cours {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nom_complet_cours;
    private SimpleStringProperty nom_aberge_cours;
    private SimpleStringProperty date_debut_cours;
    private SimpleStringProperty date_fin_cours;;
    private SimpleStringProperty category;
    private SimpleStringProperty Categorie;
    private SimpleStringProperty image;

    public Cours() {
    }
    
    
     public Cours(String title, String author,String description,String date,String category) {
        this.nom_complet_cours = new SimpleStringProperty(title);
        this.nom_aberge_cours = new SimpleStringProperty(author);
        this.date_debut_cours = new SimpleStringProperty(description);
        this.date_fin_cours = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        
    }

   
    public Cours(String title, String author,String description,String image) {
        this.nom_complet_cours = new SimpleStringProperty(title);
        this.nom_aberge_cours = new SimpleStringProperty(author);
        this.date_debut_cours = new SimpleStringProperty(description);
        this.image = new SimpleStringProperty(image);
    }
    

    
    

    public Cours(String title, String author,String description,String date,String category,String url,String image) {
        this.nom_complet_cours = new SimpleStringProperty(title);
        this.nom_aberge_cours = new SimpleStringProperty(author);
        this.date_debut_cours = new SimpleStringProperty(description);
        this.date_fin_cours = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        this.Categorie = new SimpleStringProperty(url);
        this.image = new SimpleStringProperty(image);
    }
    public Cours(int id,String title, String author,String description,String date,String category,String url,String image) {
        this.id = new SimpleIntegerProperty(id);
        this.nom_complet_cours = new SimpleStringProperty(title);
        this.nom_aberge_cours = new SimpleStringProperty(author);
        this.date_debut_cours = new SimpleStringProperty(description);
        this.date_fin_cours = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        
        this.Categorie = new SimpleStringProperty(url);
        this.image = new SimpleStringProperty(image);
    }
    
    public Cours(int id,String title, String author,String description,String date,String category) {
        this.id = new SimpleIntegerProperty(id);
        this.nom_complet_cours = new SimpleStringProperty(title);
        this.nom_aberge_cours = new SimpleStringProperty(author);
        this.date_debut_cours = new SimpleStringProperty(description);
        this.date_fin_cours = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        
    }
    
    
    
     public String getImage(){
        return image.get();
        
    }
     public void setImage(String image){
        this.image = new SimpleStringProperty(image);
    }
     
    public String getUrl(){
        return Categorie.get();
        
    }
    public void setUrl(String url){
        this.Categorie = new SimpleStringProperty(url);
    }
    public String getCategory(){
        return category.get();
    }
    
    public void setCategory(String category){
        this.category = new SimpleStringProperty(category);
    }

    public String getDate(){
        return date_fin_cours.get();
    }
    
public void setDate(String date){
        this.date_fin_cours = new SimpleStringProperty(date);
    }
    
    
    

    

    public String getDescription(){
        return date_debut_cours.get();
    }
    public void setDescription(String description){
        this.date_debut_cours = new SimpleStringProperty(description);
    }
    
    
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getTitle() {
        return nom_complet_cours.get();
    }

    public void setTitle(String title) {
        this.nom_complet_cours =new SimpleStringProperty (title);
    }
    
    
    
    

    public void setAuthor(String author) {
        this.nom_aberge_cours = new SimpleStringProperty(author);
    }

    public String getAuthor() {
        return nom_aberge_cours.get();
    }

   
    
    
    
    
    public SimpleIntegerProperty getIdProperty() {
        return id;
    }
    
    public SimpleStringProperty getTitleProperty(){
        return nom_complet_cours;
    }
    public SimpleStringProperty getAuthorProperty(){
        return nom_aberge_cours;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return date_debut_cours;
    }
    
     public SimpleStringProperty getDateProperty(){
        return date_fin_cours;
    }
     
     public SimpleStringProperty getCategoryProperty(){
        return category;
    }
     
     public SimpleStringProperty getUrlProperty(){
        return Categorie;
    }
     
      public SimpleStringProperty getImageProperty(){
        return image;
    }
    

    @Override
    public String toString() {
        return "Cours{" + "id=" + id.get() + ", title=" + nom_complet_cours.get() + ", author=" + nom_aberge_cours.get() + ", description=" + date_debut_cours.get() +", date=" + date_fin_cours.get() +", category=" + category.get() + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cours other = (Cours) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}
