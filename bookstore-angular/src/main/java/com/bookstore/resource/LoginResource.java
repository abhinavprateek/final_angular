package com.bookstore.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.security.Authority;
import com.bookstore.service.UserService;

@RestController
public class LoginResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/token")
	public @ResponseBody Map<String,String> token(HttpSession session, HttpServletRequest request){
		
		System.out.println("------------------------------------------------------------");
		System.out.println("                TOKEN API HAS BEEN HIT");
		System.out.println("------------------------------------------------------------");
		//System.out.println(request.getRemoteHost());
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(remoteHost+":"+portNumber);
		System.out.println(request.getRemoteAddr());
		return Collections.singletonMap("token", session.getId());
	}
	//Check both the session and the role of the user as well.
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		
		Collection<Authority> authorities = (Collection<Authority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println("------------------------------------------------------------");
		System.out.println("                Check Session API HAS BEEN HIT");
		System.out.println(authorities);
		Iterator<Authority> iterator = authorities.iterator();
		while (iterator.hasNext()) {
	        System.out.println("value= " + iterator.next().getAuthority());
	    }
		System.out.println("------------------------------------------------------------");		

		
		return new ResponseEntity(authorities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public ResponseEntity logout() {
		
		System.out.println("------------------------------------------------------------");
		System.out.println("                LOGOUT Session API HAS BEEN HIT");
		System.out.println("------------------------------------------------------------");		
		SecurityContextHolder.clearContext();
		return new ResponseEntity("Logout Successfull", HttpStatus.OK);
	}
	
	
}
