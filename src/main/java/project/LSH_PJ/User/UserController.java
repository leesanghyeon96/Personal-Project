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
	
//	@GetMapping(value = "/fbdetail/member")
//	public String fmember(Model model) {
//		model.addAttribute("userDto", new UserDto());
//		return "redirect:/member";
//	}
//	
	
	
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
	
	
	
	
//	@PostMapping("/member")
//	public String member(@Valid UserDto userDto, BindingResult bindingResult) {
//		
//		if(bindingResult.hasErrors()) {
//			return "member";
//		}
//		
//		if(!userDto.getPassword2().equals(userDto.getPassword1())) {
//			bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
//			
//			return "member";
//		}
//		
//		try {
//			 //userService.create(userDto.getUsername(), userDto.getEmail(), userDto.getPassword1(), userDto.get);
//			 SiteUser siteUser = SiteUser.createSiteUser(userDto, passwordEncoder);
//			 
//			 
//		}catch(DataIntegrityViolationException e) {
//			
//			 e.printStackTrace();
//			 bindingResult.reject("memberFailed", "이미 등록된 사용자입니다.");
//			 
//			 return "member";
//			 
//		}catch(Exception e) {
//			 e.printStackTrace();
//			 bindingResult.reject("memberFailed", e.getMessage());
//			 
//			 return "member";
//		}
//		
//		//userService.create(userDto.getUsername(), userDto.getEmail(), userDto.getPassword1());
//		
//		return "redirect:/";
//		
//		
//	}
	
	

	
	
}
