/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;
//import java.time.LocalDate;


/**
 *
 * @author ASUS
 */
public class Offre {
    
    private int IdOffre,Tel,Experience,NombreHeure,Salaire;
    private String NomEntreprise,Description,Localisation,
            TypeContrat,NiveauEtude,Langue,Email;
    private java.sql.Date Date;
    private int categ;
    Categorie categorie;
    //private LocalDate d;


    public Offre(){
    }
    
      public Offre(int IdOffre, String NomEntreprise, int Salaire, String Description, String Localisation, int NombreHeure, String TypeContrat, String NiveauEtude,  int Experience , String Langue, Date Date, int Tel, String Email) {
        this.IdOffre = IdOffre;
        this.NomEntreprise = NomEntreprise;
        this.Salaire = Salaire;     
        this.Description = Description;        
        this.Localisation = Localisation;
        this.NombreHeure = NombreHeure;
        this.TypeContrat = TypeContrat;
        this.NiveauEtude = NiveauEtude;
        this.Experience = Experience;
        this.Langue = Langue;
        this.Date = Date;
        this.Tel = Tel;
        this.Email = Email;
    }
    
    public Offre(int IdOffre, int categ, String NomEntreprise, int Salaire, String Description, String Localisation, int NombreHeure, String TypeContrat, String NiveauEtude,  int Experience , String Langue, Date Date, int Tel, String Email) {
        this.IdOffre = IdOffre;
        this.categ = categ;
        this.NomEntreprise = NomEntreprise;
        this.Salaire = Salaire;     
        this.Description = Description;        
        this.Localisation = Localisation;
        this.NombreHeure = NombreHeure;
        this.TypeContrat = TypeContrat;
        this.NiveauEtude = NiveauEtude;
        this.Experience = Experience;
        this.Langue = Langue;
        this.Date = Date;
        this.Tel = Tel;
        this.Email = Email;
    }

    public int getIdOffre() {
        return IdOffre;
    }

    public void setIdOffre(int IdOffre) {
        this.IdOffre = IdOffre;
    }

    public int getTel() {
        return Tel;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int Experience) {
        this.Experience = Experience;
    }

    public int getNombreHeure() {
        return NombreHeure;
    }

    public void setNombreHeure(int NombreHeure) {
        this.NombreHeure = NombreHeure;
    }

    public int getSalaire() {
        return Salaire;
    }

    public void setSalaire(int Salaire) {
        this.Salaire = Salaire;
    }

    public String getNomEntreprise() {
        return NomEntreprise;
    }

    public void setNomEntreprise(String NomEntreprise) {
        this.NomEntreprise = NomEntreprise;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }

    public String getTypeContrat() {
        return TypeContrat;
    }

    public void setTypeContrat(String TypeContrat) {
        this.TypeContrat = TypeContrat;
    }

    public String getNiveauEtude() {
        return NiveauEtude;
    }

    public void setNiveauEtude(String NiveauEtude) {
        this.NiveauEtude = NiveauEtude;
    }

    public String getLangue() {
        return Langue;
    }

    public void setLangue(String Langue) {
        this.Langue = Langue;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getCateg() {
        return categ;
    }

    public void setCateg(int categ) {
        this.categ = categ;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    @Override
    public String toString() {
        return "Offre{" + "IdOffre=" + IdOffre /*+ ", NomCategorieId=" + categ*/ + ", NomEntreprise=" + NomEntreprise + ", Salaire=" + Salaire + ", Description=" + Description + ", Localisation=" + Localisation + ", NombreHeure=" + NombreHeure + ", TypeContrat=" + TypeContrat + ", NiveauEtude=" + NiveauEtude +", Experience=" + Experience + ", Langue=" + Langue +", Date=" + Date +", Tel=" + Tel +  ", Email=" + Email + '}';
    }
    
    
}
