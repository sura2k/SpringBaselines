package com.sura2k.examples.springsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "/logut")
	public String logout(){
		return "logout";
	}
	
	@RequestMapping(value = "/reg")
	public String reg(){
		return "reg";
	}
	
	@RequestMapping(value = "/home")
	public String home(){
		return "home";
	}
}
