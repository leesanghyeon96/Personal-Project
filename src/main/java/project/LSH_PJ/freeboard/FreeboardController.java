package project.LSH_PJ.freeboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FreeboardController {
	
	// 03-07 자유게시판 getMapping 생성
	@GetMapping("/freeboard")
	public String freeboard() {
		return "freeboard";
	}
}
