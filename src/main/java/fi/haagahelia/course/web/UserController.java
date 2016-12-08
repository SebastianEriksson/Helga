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
import fi.haagahelia.course.domain.MemberRepository;

@Controller
public class UserController {
	@Autowired
	private MemberRepository repository;
	
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
	
		    	Member newMember = new Member();
		    	newMember.setPasswordHash(hashPwd);
		    	newMember.setUsername(signupForm.getUsername());
		    	newMember.setRole("USER");
		    	newMember.setFirstName(signupForm.getFirstName());
		    	newMember.setSurname(signupForm.getSurname());
		    	newMember.setEmail(signupForm.getEmail());
		    	newMember.setValid(signupForm.getValid());
		    	newMember.setMembership("yes");
		    	newMember.setPosition(signupForm.getPosition());
		    	
		    	
		    	if (repository.findByUsername(signupForm.getUsername()) == null && repository.findByEmail(signupForm.getEmail()) == null) { // Check if user or email exists
		    		repository.save(newMember);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");
	    			bindingResult.rejectValue("email", "err.email", "Email already exsists");
	    			return "signup";
		    	}
			}
			else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
				return "signup";
			}
		}
		else {
			return "signup";
		}
		return "redirect:/login";
	}
}