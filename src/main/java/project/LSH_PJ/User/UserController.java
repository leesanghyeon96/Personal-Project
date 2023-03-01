package project.LSH_PJ.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping(value = "/member")
	public String member(UserCreateForm userCreateForm) {
		return "member";
	}
	
}
