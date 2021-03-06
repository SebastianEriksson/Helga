package fi.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Create position entity
@Entity
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int membershipid;

	private String name;
	
	// Link to member class
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "membership")
	private List<Member> members;
	
	// Getters and setters
	public Membership() {}
	
	public Membership(String name) {
		super();
		this.name = name;
	}
	
	public int getMembershipid() {
		return membershipid;
	}
	public void setMembershipid(int membershipid) {
		this.membershipid = membershipid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	@Override // toString
	public String toString() {
		return "Membership [membershipid=" + membershipid + ", name=" + name + "]";
	}
}
