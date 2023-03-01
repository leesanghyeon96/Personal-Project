package project.LSH_PJ.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class memberController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/member")
	public String member() {
		return "member";
	}
}
