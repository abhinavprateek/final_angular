package com.bookstore.domain.security;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_role")	//If we do not give this name, java persistence will use the default one.
public class UserRole implements Serializable  {
	
	private static final long serialVersionUID = 123123L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	//For Auto Incrementing the id.
	private long userRoleId;
	
	public UserRole(){}
	
	public UserRole(User user, Role role){
		this.user = user;
		this.role = role;
	}
	
//	LAZY = fetch when needed
//	EAGER = fetch immediately	
//	EAGER loading of collections means that they are fetched fully at the time their parent is fetched. So if you have Course and it has  List<Student>, 
//	all the students are fetched from the database at the time the Course is fetched.	
//	LAZY on the other hand means that the contents of the List are fetched only when you try to access them. For example, by calling course.getStudents().iterator()

//The annotation @JoinColumn indicates that this entity is the owner of the relationship (that is: the corresponding table has a column with a foreign key to the 
//	referenced table), whereas the attribute mappedBy indicates that the entity in this side is the inverse of the relationship, and the owner resides in the "other" entity.
	
	
	@ManyToOne(fetch = FetchType.EAGER)	//Should be Lazy
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	
}
