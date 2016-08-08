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

@WebServlet("/membership_info")
public class MembershipAccessDeniedController extends HttpServlet {

    private static final long serialVersionUID = -4340500321965553349L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	boolean loggedIn = LoginChecker.isLoggedIn(session);
    	if(loggedIn){
	    	request.getRequestDispatcher(Pages.MEMBERSHIP_INFO_PAGE).forward(request, response);
    	}else{
    		String loginURI = request.getContextPath() + "/login";
    		response.sendRedirect(loginURI);
    		// If not logged in redirect to login page
    		// Even this is marked as a non secured resource, this detail only available for a user, there for did this check
    		// Otherwise you can do something else
    	}
    }

}