package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelgaController {
	
	// Takes user to login page when connecting
	@RequestMapping(value ="/")
	public String index() {
		return "login";
	}
}
