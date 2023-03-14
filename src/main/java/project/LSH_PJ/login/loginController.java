package project.LSH_PJ.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	//03-13
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "redirect:/login";
	}
}
