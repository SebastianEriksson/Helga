package fi.haagahelia.course.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
		
	// Password can't be null
	@Column(name = "password", nullable = false)
	private String passwordHash;
		
	// Insert position
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "roleid")
	private Role role;
	
	// Getters and setters
	public User() {
	}
	
	public User(String username, String passwordHash, Role role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String toString() {
		return "User [id=" + id + ", username=" + username + 
				", passwordHash=" + passwordHash + 
				", role=" + this.getRole() + "]";
	}
	
}