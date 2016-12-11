package fi.haagahelia.course.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.SignupForm;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;
import fi.haagahelia.course.domain.MemberRepository;
import fi.haagahelia.course.domain.Membership;
import fi.haagahelia.course.domain.MembershipRepository;
import fi.haagahelia.course.domain.Role;
import fi.haagahelia.course.domain.RoleRepository;

@Controller
public class UserController {
	@Autowired
	private MemberRepository repository;
	@Autowired
	private MembershipRepository mrepository;
	@Autowired
	private RoleRepository rrepository;
	@Autowired
	private UserRepository urepository;
	
	@RequestMapping(value = "signup")
	public String addBook(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

/**
 * Create new user
 * Check if user already exists & form validation
 * 
 * @param signupForm
 * @param bindingResult
 * @return
 */

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	
		    	// Enter Member details
		    	Member newMember = new Member();
		    	newMember.setFirstName(signupForm.getFirstName());
		    	newMember.setSurname(signupForm.getSurname());
		    	newMember.setEmail(signupForm.getEmail());
		    	newMember.setValid(signupForm.getValid());
		    	
		    	// Enter Membership details
		    	Membership newMembership = new Membership();
		    	newMembership.setName("member");
		    	
		    	// Enter user details
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	
		    	// Enter role details
		    	Role newRole = new Role();
		    	newRole.setName("USER");
		    	
		    	
		    	if (urepository.findByUsername(signupForm.getUsername()) == null && repository.findByEmail(signupForm.getEmail()) == null) { // Check if user or email exists
		    		repository.save(newMember); // save member data
		    		mrepository.save(newMembership); // save membership data
		    		rrepository.save(newRole); // save role data
		    		urepository.save(newUser); // save user data
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists"); // give error if one exsists already
	    			bindingResult.rejectValue("email", "err.email", "Email already exsists"); // give error if one exsists already
	    			return "signup";
		    	}
			}
			else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match"); // give error if the passwords don't match	
				return "signup";
			}
		}
		else {
			return "signup";
		}
		return "redirect:/login";
	}
}