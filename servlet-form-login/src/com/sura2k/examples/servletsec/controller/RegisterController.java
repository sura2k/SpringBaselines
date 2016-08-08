package com.sura2k.examples.servletsec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sura2k.examples.servletsec.controller.consts.Pages;
import com.sura2k.examples.servletsec.controller.util.LoginChecker;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = -4340500321965543349L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean loggedIn = LoginChecker.isLoggedIn(session);
        
        if(loggedIn){
        	response.sendRedirect(request.getContextPath() + "/home");
        	// If someone accessing the registration page even after login, need to prevent and redirect to a default page. here default path is /home
        }else{
        	request.getRequestDispatcher(Pages.REGISTER_PAGE).forward(request, response);
        	// Not logged in serve the resource
        }
    }

}