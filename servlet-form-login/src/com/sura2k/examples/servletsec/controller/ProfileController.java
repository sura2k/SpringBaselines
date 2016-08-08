package com.sura2k.examples.servletsec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sura2k.examples.servletsec.controller.consts.Pages;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = -4340500321965543349L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Pages.PROFILE_PAGE).forward(request, response);
    }

}