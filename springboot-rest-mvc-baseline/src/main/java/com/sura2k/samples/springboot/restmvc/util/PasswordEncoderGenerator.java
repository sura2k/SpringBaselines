package com.sura2k.samples.springboot.restmvc.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	public static String encode(String plainText){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(plainText);
	}
	
	public static void main(String[] args) {
		System.out.println(encode("admin"));
		System.out.println(encode("manager"));
		System.out.println(encode("employee"));
	}
}