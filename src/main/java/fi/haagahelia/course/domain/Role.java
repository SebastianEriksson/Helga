package fi.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// Create position entity
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	private List<User> users;
	
	// Getters and setters
	public Role() {}
	
	public Role(String name) {
		super();
		this.name = name;
	}
	
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", name=" + name + "]";
	}
}
