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

import fi.haagahelia.course.domain.AddAccount;
import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.SignupForm;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;
import fi.haagahelia.course.domain.MemberRepository;
import fi.haagahelia.course.domain.Membership;
import fi.haagahelia.course.domain.MembershipRepository;

@Controller
public class UserController {
	@Autowired
	private MemberRepository repository;
	@Autowired
	private MembershipRepository mrepository;
	@Autowired
	private UserRepository urepository;
	
	// This is the controller for new users to sign up through signup.html
	@RequestMapping(value = "signup")
	public String signUp(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	// Validation and check no one else is using the same user
	// If no errors occur save user
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	
		    	// Enter Membership details
		    	Membership newMembership = new Membership(signupForm.getMembershipName());
		    	
		    	// Enter user details
		    	User newUser = new User(
		    			signupForm.getUsername(), 
		    			hashPwd, 
		    			"USER");
		    	
		    	// Enter Member details
		    	Member newMember = new Member(
		    			signupForm.getFirstName(), 
		    			signupForm.getSurname(),
		    			signupForm.getEmail(), 
		    			signupForm.getValid(), 
		    			newMembership, 
		    			newUser);
		    	
		    	if (urepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		mrepository.save(newMembership); // save membership data
		    		urepository.save(newUser); // save user data
		    		repository.save(newMember); // save member data
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists"); // give error if one exsists already
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
		return "redirect:/login"; // After successful signup, redirect to login
	}
	
	// This is the controller for admins to add new users in addaccount.html
	@RequestMapping(value = "addaccount")
	public String addAccount(Model model) {
		model.addAttribute("addaccount", new AddAccount());
		model.addAttribute("memberships", mrepository.findAll());
		return "addaccount";
	}
	
	// Validation and check no one else is using the same user
	// If no errors occur save user
	@RequestMapping(value = "addaccount", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("addaccount") AddAccount addAccount, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (addAccount.getPassword().equals(addAccount.getPasswordCheck())) { // check password match		
	    		String pwd = addAccount.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	
		    	// Enter Membership details
		    	Membership newMembership = new Membership(
		    			addAccount.getName());
		    	
		    	// Enter user details
		    	User newUser = new User(
		    			addAccount.getUsername(), 
		    			hashPwd, 
		    			addAccount.getRole());
		    	
		    	// Enter Member details
		    	Member newMember = new Member(
		    			addAccount.getFirstName(), 
		    			addAccount.getSurname(),
		    			addAccount.getEmail(), 
		    			addAccount.getValid(), 
		    			newMembership, 
		    			newUser);
		    	
		    	if (urepository.findByUsername(addAccount.getUsername()) == null) { // Check if username exists
		    		mrepository.save(newMembership); // save membership data
		    		urepository.save(newUser); // save user data
		    		repository.save(newMember); // save member data
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists"); // give error if one exsists already
	    			return "addaccount";
		    	}
			}
			else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match"); // give error if the passwords don't match	
				return "addaccount";
			}
		}
		else {
			return "addaccount";
		}
		return "redirect:/memberlist"; // After successful account addition, redirect to list
	}
}