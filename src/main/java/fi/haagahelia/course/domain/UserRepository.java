package fi.haagahelia.course.domain;

import org.springframework.data.repository.CrudRepository;

//User repo
//Allows controllers to find all or certain users with pre-defined search criterias
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
}
