package fi.haagahelia.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// Define Member entity
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String firstName;
	private String surname;
	private String email;
	private double valid;
	private String membership;
	
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	// Password can't be null
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	// Defines the role (member, moderator, admin)
	@Column(name = "role", nullable = false)
	private String role;
	
	// Insert position
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "positionid")
	private Position position;
	
	// Getters and setters
	public Member() {}
	
	public Member(String firstName, String surname, String email, double valid, 
			String membership, String username, String passwordHash, String role) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.valid = valid;
		this.membership = membership;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getValid() {
		return valid;
	}
	public void setValid(double valid) {
		this.valid = valid;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
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
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		if (this.position != null)
			return "Member [id=" + id + ", first name=" + firstName + ", surname=" + 
					surname + ", email=" + email + ", valid to=" + valid +
					", membership=" + membership + ", position=" + this.getPosition() + "]";
		else
			return "Member [id=" + id + ", first name=" + firstName + ", surname="+
					surname + ", email=" + email + ", valid to=" + valid +
					", membership=" + membership + "]";
	}
	
	
}
