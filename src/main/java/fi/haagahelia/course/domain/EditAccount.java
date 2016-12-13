package fi.haagahelia.course.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

//This class is used when a admin edits a account
//In this class everything except date is customizable in the editaccount.html
//None of the values can be empty
public class EditAccount {
	
	@NotEmpty
	@Size(min=5, max=30)
	private String username = "";
 
	// Passwords are entered here in plain (non-hashed)
	@NotEmpty
	@Size(min=7, max=30)
	private String password = "";
	
	@NotEmpty
	@Size(min=7, max=30)
	private String passwordCheck = "";
	
	@NotEmpty
	private String role = "";
	 
	@NotEmpty
	@Size(min=2, max=30)
	private String firstName = "";
	 
	@NotEmpty
	@Size(min=2, max=30)
	private String surname = "";
	 
	@NotEmpty
	@Size(min=6, max=60)
	private String email = "";
	 
	// Defines how long a membership is valid, default is current date + 12 months
	@NotEmpty
	private String valid = "";
	 
	// Membership name
	@NotEmpty
	private String name = "";
 
	// Getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
}