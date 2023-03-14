package project.LSH_PJ.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping(value = "/member")
	public String member(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "member";
	}
	
	
	
	@PostMapping(value = "/member")
	public String member(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member";
		}
		
		try {
			SiteUser siteUser = SiteUser.createSiteUser(userDto, passwordEncoder);
			userService.saveUser(siteUser);
			
		}catch(IllegalStateException e) {
			model.addAttribute("erroeMessage", e.getMessage());
			return "member";
		}
		
		return "redirect:/";
	}
	
	

	

	
	
}
