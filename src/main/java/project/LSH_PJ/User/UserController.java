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
	public String member(UserDto userDto) {
		return "member";
	}
	
	
	@PostMapping("/member")
	public String member(@Valid UserDto userDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "member";
		}
		
		if(!userDto.getPassword2().equals(userDto.getPassword1())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
			
			return "member";
		}
		

		
		userService.create(userDto.getUsername(), userDto.getEmail(), userDto.getPassword1());
		
		return "redirect:/";
		
		
	}
	
	

	
	
}
