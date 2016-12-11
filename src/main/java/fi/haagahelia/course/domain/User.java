package fi.haagahelia.course.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long userid;
	
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
		
	// Password can't be null
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	// Role can't be null
	@Column(name = "role", nullable = false)
	private String role;
		
//	// Insert position
//	@ManyToOne
//	@JsonIgnore
//	@JoinColumn(name = "roleid")
//	private Role role;
	
	// Getters and setters
	public User() {
	}
	
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public Long getId() {
		return userid;
	}
	public void setId(Long userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + 
				", passwordHash=" + passwordHash + 
				", role=" + role + "]";
	}
	
}