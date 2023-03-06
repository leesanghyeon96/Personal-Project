package project.LSH_PJ.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping(value = "/member")
	public String member(UserCreateForm userCreateForm) {
		return "member";
	}
	
	@PostMapping("/member")
	public String member(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "member";
		}
		
		if(!userCreateForm.getPassword2().equals(userCreateForm.getPassword1())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
			
			return "member";
		}
		

		
		userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		
		return "redirect:/";
		
		
	}
	
	

	
	
}
