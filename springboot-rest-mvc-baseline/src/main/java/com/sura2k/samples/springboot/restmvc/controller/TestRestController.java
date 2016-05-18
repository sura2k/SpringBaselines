package com.sura2k.samples.springboot.restmvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sura2k.samples.springboot.restmvc.model.BasicUserInfo;

@RestController
public class TestRestController {
	
	/*
	 * Just to get it work, otherwise use a custom user details class
	 */
	@RequestMapping("/rest/common/basicuserinfo")
	public BasicUserInfo basicInfo(Authentication authentication){
		User user = (User)authentication.getPrincipal();
		for(GrantedAuthority authority: user.getAuthorities()){
			return new BasicUserInfo(user.getUsername(), authority.getAuthority());
		}
		return null;
	}
	
	@RequestMapping("/rest/admin")
	public String testAdmin(Authentication authentication){
		return "It is working d "+authentication.getName()+"! You have "+authentication.getAuthorities()+"perms. pw="+authentication.getCredentials()+", prnc="+authentication.getPrincipal();
	}
	
	@RequestMapping("/rest/manager")
	public String testManager(Authentication authentication){
		return "It is working r "+authentication.getName()+"! You have "+authentication.getAuthorities()+"perms. pw="+authentication.getCredentials()+", prnc="+authentication.getPrincipal();
	}
}
