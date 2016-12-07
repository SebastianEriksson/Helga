package fi.haagahelia.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "positionid")
	private Position position;
	
}
