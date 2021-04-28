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
public class Affectation {
    
    private int IdOffre;
    private int IdUser;

    public Affectation(int IdOffre, int IdUser) {
        this.IdOffre = IdOffre;
        this.IdUser = IdUser;
    }

    public int getIdOffre() {
        return IdOffre;
    }

    public void setIdOffre(int IdOffre) {
        this.IdOffre = IdOffre;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    @Override
    public String toString() {
        return "Affectation{" + "IdOffre=" + IdOffre + ", IdUser=" + IdUser + '}';
    }
    
}
