package fi.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.MemberRepository;

/**
 * This class is used by spring security to authenticate and authorize user
 **/

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final MemberRepository repository;
	
	// Use correct Repo
	@Autowired
	public UserDetailServiceImpl(MemberRepository userRepository) {
		this.repository = userRepository;
	}
	
	// Load the user from the Member class and search through MemberRepo
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member curruser = (Member) repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
