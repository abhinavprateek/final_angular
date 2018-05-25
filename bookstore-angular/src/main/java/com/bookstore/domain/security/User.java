package com.bookstore.domain.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Provides core user information
//Implementations are not used directly by Spring Security for security purposes. They simply store user information which is later encapsulated into Authentication objects. This allows non-security related user information (such as email addresses, telephone numbers etc) to be stored in a convenient location.
@Entity
public class User implements UserDetails, Serializable  {
	
	private static final long serialVersionUID = 123123L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	//For Auto Incrementing the id.
	@Column(name="Id", nullable=false, updatable=false)
	private Long id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	private String email;
	private String phone;
	private boolean enable = true;


//The meaning of CascadeType.ALL is that the persistence will propagate (cascade) all EntityManager operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) to 
//	the relating entities.	

//	The mappedBy property here, must have the same name as the @JoinColumn propert name of the class (User user => in this case.)¨
//	The @JsonIgnore annotated properties/methods will not be serialized/deserialized by Jackson. While the not annotated will be.
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	
	
//	private ShoppingCart shoppingCart;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (UserRole ur : userRoles) {
			 authorities.add(new Authority(ur.getRole().getName() ) );
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enable;
	}
	
	

}
