package com.devmobile.cs2.esi_lib.backend;

import com.devmobile.cs2.esi_lib.backend.Models.User;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetUserByNomEtMPasseServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase dataBase =new DataBase() ;
        String nom = req.getParameter("nom") ;
        String passe = req.getParameter("passe") ;
        User user =dataBase.getUserByNomEtMpasse(nom,passe) ;

        Gson gson =new Gson() ;
        resp.getWriter().print(gson.toJson(user+" "+nom+" "+passe)) ;
    }
}
