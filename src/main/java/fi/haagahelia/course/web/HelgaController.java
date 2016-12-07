package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelgaController {
	
	
	@RequestMapping(value ="/")
	public String index() {
		return "index";
	}
}
