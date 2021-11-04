package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( "/deconnectionClientWeb" )
public class DeconnectionClientWeb extends HttpServlet {
    private static final long  serialVersionUID = 1L;
    private static final String ATT_DECONNECTION = "deconnection";
    private static final String URL_REDIRECTION  = "http://localhost:8088/siteWeb/Accueil";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession();
        session.setAttribute( ATT_DECONNECTION, "true" );
        response.sendRedirect( URL_REDIRECTION );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }
}
