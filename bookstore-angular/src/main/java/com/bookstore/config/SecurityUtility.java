package com.bookstore.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtility {
	//will be used to encode our password
	private static final String SALT = "salt"; // Salt should be protected carefully
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	@Bean
	public static String randomPassword(){
		String SALCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder(); 
		Random rnd = new Random();
		
		while(salt.length() < 18){
				int index = (int) (rnd.nextFloat() * SALCHARS.length());
				salt.append(SALCHARS.charAt(index));
		}
		
		String saltStr = salt.toString();
		return saltStr;
		
	}
	

}
