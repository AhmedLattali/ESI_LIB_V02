package com.devmobile.cs2.esi_lib.backend.Models;

import java.util.ArrayList;

/**
 * Created by Ahmed-PC on 21-05-2015.
 */
public class Livre {

    private int id ;

    private String titre ;
    private String auteur ;
    private String categorie ;
    private String annee ;
    private String description;
    private byte[] image;
    private ArrayList<String> tags ;
    public Livre(){

    }

    public Livre(byte[] image ,String titre,String auteur,String categorie,String annee,String description, ArrayList<String> tags){

        this.setImage(image);
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setCategorie(categorie);
        this.setAnnee(annee);
        this.setDescription(description);
        this.setTags(tags);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    public void setTagsFromString(String sttags){
        String[] words = sttags.split(",") ;
        this.tags=new ArrayList<String>() ;
        for(String word : words){
            this.tags.add(word) ;
        }


    }
}
