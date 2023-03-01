package project.LSH_PJ.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
