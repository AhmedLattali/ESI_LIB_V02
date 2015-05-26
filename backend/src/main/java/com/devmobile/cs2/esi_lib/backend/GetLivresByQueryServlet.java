package com.devmobile.cs2.esi_lib.backend;

import com.devmobile.cs2.esi_lib.backend.Models.Livre;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetLivresByQueryServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase dataBase =new DataBase() ;
        String query = req.getParameter("query") ;
        List<Livre> list =dataBase.getLivresByQuery(query) ;
        Gson gson =new Gson() ;

        resp.getWriter().print(gson.toJson(list)) ;
    }
}
