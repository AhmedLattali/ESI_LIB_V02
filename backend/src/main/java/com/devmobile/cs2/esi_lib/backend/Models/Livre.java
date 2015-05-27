package com.devmobile.cs2.esi_lib.backend.Models;

import java.util.ArrayList;

public class Livre {

    private int id ;

    private String titre ;
    private String auteur ;
    private String categorie ;
    private String annee ;
    private String description;
    private byte[] image;
    private ArrayList<String> tags ;
    private float rating ;
    private int nbr_rating ;

    public Livre(){

    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNbr_rating() {
        return nbr_rating;
    }

    public void setNbr_rating(int nbr_rating) {
        this.nbr_rating = nbr_rating;
    }

    public Livre(byte[] image ,String titre,String auteur,String categorie,String annee,String description, ArrayList<String> tags,float rating,int nbr_rating){

        this.image = image;
        this.titre = titre;
        this.auteur=auteur;
        this.categorie =categorie;
        this.annee=annee;
        this.description = description;
        this.setTags(tags);
        this.rating=rating ;
        this.nbr_rating=nbr_rating;

    }

    public byte[] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
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

    public boolean rechercheMotClé(String word){
        if (this.titre.toLowerCase().indexOf(word.toLowerCase()) != -1
                || this.categorie.toLowerCase().indexOf(word.toLowerCase()) != -1
                || this.auteur.toLowerCase().indexOf(word.toLowerCase()) != -1
                || this.description.toLowerCase().indexOf(word.toLowerCase()) != -1 || rechercheTags(word)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean rechercheTags(String word){

        // Log.i("word", word) ;
        for(String str : this.getTags()){
            // Log.i("word", word) ;
            // Log.i("str", str) ;
            if(str.toLowerCase().indexOf(word.toLowerCase())!=-1){

                return true ;
            }
        }
        return  false ;
    }
    public boolean rechercheMotsClé(String sentence){
        String[] words = sentence.split("\\s+");
        boolean bool = true;
        int i=0;
        while (i<words.length && bool==true){
            if (this.rechercheMotClé(words[i])==false) {
                bool=false;
            }
            else {
                i++;
            }
        }
        return bool;
    }


    public ArrayList<String> getTags() {
        return tags;
    }

    public String getTagsString() {
        String stringTags =new String() ;

        boolean first = true;
        for(String str : this.getTags()){
            if(first ==true){
                stringTags=str ;
            }
            else{
                stringTags=stringTags+","+str ;
                first=false ;
            }

        }
        return stringTags ;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}