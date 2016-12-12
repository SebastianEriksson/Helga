package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.Member;
import fi.haagahelia.course.domain.MemberRepository;
import fi.haagahelia.course.domain.UserRepository;

@Controller
public class MemberController {
	// Get the member list
	@Autowired
	private MemberRepository repository;
	
	// Get the user list
	@Autowired
	private UserRepository urepository;
	
	// Takes user to login page when connecting
	@RequestMapping(value="/")
	public String index() {
		return "login";
	}
	
	// Login page for users and admins
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
//	// Error page for denied users
//	@RequestMapping(value="/403")
//	public String denied() {
//		return "403";
//	}
	
	@RequestMapping(value="/memberlist")
	public String memberList(Model model) {
		model.addAttribute("members", repository.findAll());
		model.addAttribute("users", urepository.findAll());
		return "memberlist";
	}
	
	// Show all members through a RESTful GET method
	@RequestMapping(value="members", method = RequestMethod.GET)
	public @ResponseBody List<Member> MembertRest() {
		return (List<Member>) repository.findAll();
	}
	
	// Show specific member through RESTful GET method by id
	@RequestMapping(value="/member/{id}", method = RequestMethod.GET)
	public @ResponseBody Member findMemberRest(@PathVariable("id") Long memberId) {
		return repository.findOne(memberId);
	}
	
	// Remove a member from the db
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteMember(@PathVariable("id") Long memberId, Model model) {
		repository.delete(memberId);
		return "redirect:../memberlist";
	}    

}
