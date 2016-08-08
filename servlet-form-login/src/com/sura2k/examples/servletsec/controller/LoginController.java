package com.sura2k.examples.servletsec.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sura2k.examples.servletsec.business.User;
import com.sura2k.examples.servletsec.business.UserService;
import com.sura2k.examples.servletsec.controller.consts.Pages;
import com.sura2k.examples.servletsec.controller.util.LoginChecker;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = -4340500311965553349L;
    
	@Inject
    private UserService userService;

	// Request the login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        boolean loggedIn = LoginChecker.isLoggedIn(session);
        if(loggedIn){
        	response.sendRedirect(request.getContextPath() + "/home");
        }else{
        	request.getRequestDispatcher(Pages.LOGIN_PAGE).forward(request, response);
        }
    }

    // Login POST
    // You have to implement this properly
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<String, String>();

        if (username == null || username.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            User user = userService.find(username, password);

            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            } else {
                messages.put("login", "Unknown login, please try again");
            }  
        }

        request.setAttribute("messages", messages);
        request.getRequestDispatcher(Pages.LOGIN_PAGE).forward(request, response);
    }

}