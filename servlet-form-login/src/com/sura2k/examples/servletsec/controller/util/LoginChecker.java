package com.sura2k.examples.servletsec.controller.util;

import javax.servlet.http.HttpSession;

public class LoginChecker {

	public static boolean isLoggedIn(HttpSession session){
		return session != null && session.getAttribute("user") != null;
	}
}
