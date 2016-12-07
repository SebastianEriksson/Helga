package fi.haagahelia.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long id;
	private String firstName;
	private String surname;
	private String email;
	private double valid;
	private String membership;
	
	// Insert position
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "positionid")
	private Position position;
	
	// Getters and setters
	public Member() {}
	
	public Member(String firstName, String surname, String email, double valid, 
			String membership) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.valid = valid;
		this.membership = membership;
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
