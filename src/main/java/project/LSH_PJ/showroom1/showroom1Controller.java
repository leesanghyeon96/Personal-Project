package project.LSH_PJ.showroom1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class showroom1Controller {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/showroom1")
	public String showroom() {
		return "showroom1";
	}
	
}
