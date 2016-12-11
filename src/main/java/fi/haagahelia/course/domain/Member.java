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
	private String firstName, surname, email, valid;
	
	// Insert membership
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "membershipid")
	private Membership membership;
	
	// Getters and setters
	public Member() {}
	
	public Member(String firstName, String surname, String email, String valid, 
			Membership membership) {
		super();
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
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	@Override
	public String toString() {
		if (this.membership != null)
			return "Member [id=" + id + ", first name=" + firstName + ", surname=" + 
					surname + ", email=" + email + ", valid to=" + valid +
					", membership=" + this.getMembership() + "]";
		else
			return "Member [id=" + id + ", first name=" + firstName + ", surname="+
					surname + ", email=" + email + ", valid to=" + valid + "]";
	}
	
}
