package com.devmobile.cs2.esi_lib.Models;

/**
 * Created by Ahmed-PC on 11-04-2015.
 */
public class NavMenuItem {

    private String titre;
    private int icon;
    private String count = "10";

    public NavMenuItem(){

    }
    public NavMenuItem(String titre , int icon , String count){
        this.titre=titre ;
        this.icon= icon;
        this.count= count;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
