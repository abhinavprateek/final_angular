package com.bookstore.domain.security;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;


//Represents an authority granted to an Authentication object.
//A GrantedAuthority must either represent itself as a String or be specifically supported by an AccessDecisionManager.
//
public class Authority implements GrantedAuthority, Serializable{
	private static final long serialVersionUID = 123123L;
	private final String authority;
	public Authority(String authority){
		this.authority = authority;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}
}
