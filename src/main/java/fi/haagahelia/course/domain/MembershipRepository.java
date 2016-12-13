package fi.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//Membership repo
//Allows controllers to find all or certain memberships with pre-defined search criterias
public interface MembershipRepository extends CrudRepository<Membership, Long>{
	
	List<Membership> findByName(String name);
}
