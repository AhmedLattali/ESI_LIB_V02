package com.devmobile.cs2.esi_lib.backend.Models;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class User {

    private String nom ;
    private String mot_de_passe ;

    public User(){

    }
    public User(String nom, String mot_de_passe){
        this.nom=nom ;
        this.mot_de_passe=mot_de_passe ;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
