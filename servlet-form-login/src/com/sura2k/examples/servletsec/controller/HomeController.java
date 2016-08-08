package com.sura2k.examples.servletsec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sura2k.examples.servletsec.business.User;
import com.sura2k.examples.servletsec.business.UserRole;
import com.sura2k.examples.servletsec.controller.consts.Pages;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = -4340500321965553349L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	User user = (User)session.getAttribute("user");
    	if(UserRole.MANAGER == user.getRole()){
    		request.getRequestDispatcher(Pages.MANAGER_HOME_PAGE).forward(request, response);
    	}else if(UserRole.IC == user.getRole()){
    		request.getRequestDispatcher(Pages.IC_HOME_PAGE).forward(request, response);
    	}else{
    		request.getRequestDispatcher(Pages.UNKNOWN_HOME_PAGE).forward(request, response);
    	}
    }

}