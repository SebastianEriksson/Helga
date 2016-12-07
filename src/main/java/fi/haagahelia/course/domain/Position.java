package fi.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long positionid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "position")
	private List<Member> members;
	
	public Position() {}
	
	public Position(String name) {
		super();
		this.name = name;
	}
	
	public Long getPositionid() {
		return positionid;
	}
	public void setPositionid(Long positionid) {
		this.positionid = positionid;
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
	public void setBooks(List<Member> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "Position [positionid=" + positionid + ", name=" + name + "]";
	}
}
