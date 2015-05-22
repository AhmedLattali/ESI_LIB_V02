package com.devmobile.cs2.esi_lib.backend;


import com.devmobile.cs2.esi_lib.backend.Models.Livre;
import com.devmobile.cs2.esi_lib.backend.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine
            = "jdbc:mysql://localhost:3306/bibliodb_server";
    static final String username = "root";
    static final String password = "";

    public Connection connecter() {
        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return con;
    }

    public List<Livre> getAllLivres() {
        List<Livre> list = new ArrayList<>();
        String query = "select * from livre";
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt(1));
                livre.setTitre(rs.getString(2));
                livre.setAuteur(rs.getString(3));
                livre.setCategorie(rs.getString(4));
                livre.setAnnee(rs.getString(5));
                livre.setDescription(rs.getString(6));
                // livre.setImage(rs.getBytes(7));
                livre.setTagsFromString(rs.getString(8));

                list.add(livre);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Livre> getLivresByCateg(String categ) {
        List<Livre> list = new ArrayList<>();
        String query = "select * from livre where categorie ="+categ;
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt(1));
                livre.setTitre(rs.getString(2));
                livre.setAuteur(rs.getString(3));
                livre.setCategorie(rs.getString(4));
                livre.setAnnee(rs.getString(5));
                livre.setDescription(rs.getString(6));
                // livre.setImage(rs.getBytes(7));
                livre.setTagsFromString(rs.getString(8));

                list.add(livre);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public User getUserByNomEtMpasse(String nom , String passe) {
        User user = new User() ;
        String query = "select * from users where nom = "+nom+" and mot_de_passe = "+passe;
        try {
            Connection con = connecter();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                user.setNom(rs.getString(1));
                user.setMot_de_passe(rs.getString(2));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user ;
    }
}

