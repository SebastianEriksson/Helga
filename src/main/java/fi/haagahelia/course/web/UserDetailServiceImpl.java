package fi.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;


// Authenticate and authorize user
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository repository;
	
	// Use correct Repo
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	// Load the user from the User class and search through UserRepo
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user; // return the user login request as positive
	}
}
