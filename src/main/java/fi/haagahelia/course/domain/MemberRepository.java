package fi.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Member repo
// Allows controllers to find all or certain members with pre-defined search criterias
public interface MemberRepository extends CrudRepository<Member, Long> {
	
	List<Member> findById(Long id);
	
	List<Member> findByFirstName(String firstName);
	
	List<Member> findByEmail(String email);
}
