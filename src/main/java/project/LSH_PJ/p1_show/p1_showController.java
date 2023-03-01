package project.LSH_PJ.p1_show;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class p1_showController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/p1_show")
	public String p1_show() {
		return "p1_show";
	}
	
}
