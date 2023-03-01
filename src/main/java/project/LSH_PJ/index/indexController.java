package project.LSH_PJ.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
	
	// 02-28 (GetMapping 추가)
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
