package com.devmobile.cs2.esi_lib.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Oo on 27/05/2015.
 */
public class RateServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataBase dataBase =new DataBase() ;

        float rating = Float.parseFloat(req.getParameter("rating")) ;
        int idLivre = Integer.parseInt(req.getParameter("idLivre")) ;


        dataBase.rateLivre(idLivre,rating);

    }
}
