package com.sura2k.examples.servletsec.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.AntPathMatcher;

import com.sura2k.examples.servletsec.business.Membership;
import com.sura2k.examples.servletsec.business.User;
import com.sura2k.examples.servletsec.controller.util.LoginChecker;

// Filter everything
// You can get rid of the ALLOWED_PATTERNS section completely, if you add you secured patterns to WebFilter like this -> @WebFilter({"/home", "/profile", "/rest/*"})
@WebFilter("/*")
public class AuthFilter implements Filter {
	
	private static final Set<String> ALLOWED_PATTERNS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("/login", "/logout", "/register", "/membership_info", "/static/**")));

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login";
		String requestPath = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$","");
		
		boolean loggedIn = LoginChecker.isLoggedIn(session);
		// boolean loginRequest = request.getRequestURI().equals(loginURI);
		boolean allowedPath = isPathAllowed(requestPath);

		if (allowedPath /* || loginRequest */) {// 1. Non secured patterns should process first
			chain.doFilter(request, response);
			
		} else if (loggedIn) { // 2. Check if logged in
			Membership membership = ((User) session.getAttribute("user")).getMembership();
			if (membership != null && Membership.APPROVED == membership) { //2.1 Check for access denied cases (here it is membership is not approved)
				chain.doFilter(request, response);
				//If logged in and no access denied cases, we can proceed with the request
			} else {
				response.sendRedirect(request.getContextPath() + "/membership_info");
				// The path /membership_info has added to the ALLOWED_PATTERNS, so first it will go to that page, if the user intentionally access that URL
				// Controller for the /membership_info also checks the loggedIn status. If not loggedIn it redirects to login
				// You can remove this feature, then you should keep that page empty or something else, because if user is NOT loggedIn, no membership value (no session) available for that request
				// But with the current logic, even /membership_info path is a non secured path, controller asks for login in to serve the page
			}
		} else {
			response.sendRedirect(loginURI);
			// Not logged in, then need to redirect to login page
			// In the next round to serve the /login path, it must be added to the ALLOWED_PATTERNS
		}
		
		// chain.doFilter(), response.sendRedirect() return to here even if you think that they are magic method calls
		// Therefore it is very important to manage if or if-else conditions here to tight the request navigation
	}
	
	private boolean isPathAllowed(String requestPath){
		
		// Check for org.springframework.util.PathMatcherTests class for more information
		
		AntPathMatcher matcher = new AntPathMatcher();
		for(String pattern: ALLOWED_PATTERNS){
			if(matcher.match(pattern, requestPath)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}