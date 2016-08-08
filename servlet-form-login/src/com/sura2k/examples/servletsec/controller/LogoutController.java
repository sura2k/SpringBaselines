package com.sura2k.examples.servletsec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sura2k.examples.servletsec.controller.util.LoginChecker;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = -4340500311975553349L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }

    // After log out, redirect to login page
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean loggedIn = LoginChecker.isLoggedIn(session);
        if(loggedIn){
        	session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

}