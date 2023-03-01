package project.LSH_PJ.intro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class introController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping(value = "/intro")
	public String intro() {
		return "intro";
	}
	
	
}
