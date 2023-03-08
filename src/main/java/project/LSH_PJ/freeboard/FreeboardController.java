package project.LSH_PJ.freeboard;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.This;
import project.LSH_PJ.user.SiteUser;
import project.LSH_PJ.user.UserService;

@Controller
@RequiredArgsConstructor
public class FreeboardController {
	
	private final FreeBoardService freeboardService;
	private final UserService userService;
	
	// 03-07 자유게시판 getMapping 생성
	@GetMapping("/freeboard")
		// 03-08 페이징 처리위해 (@Re~page)수정
	public String freeboard(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		
		// 03-08 페이징 처리를 위해 아래 코드 삭제
//		List<FreeBoard> boardList =
//				this.freeboardService.getfreeBoardList();
//		model.addAttribute("boardList", boardList);
		Page<FreeBoard> paging = this.freeboardService.getList(page);
			//modle에 담기
		model.addAttribute("paging", paging);
		
		
		return "freeboard";
	}
	
	
	// 게시판 상세 페이지 03-08
	@GetMapping("/fbdetail/{id}")
	public String fbdetail(Model model, @PathVariable("id") Integer id) {
		
		//service에서 조회한 객체를 가져와 모델로 담기
		FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
		model.addAttribute("FreeBoard", freeBoard);
		
		
		return "fbdetail";
	}
	
	
	//게시판 글 등록 페이지 getMapping 03-08
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/fbcreate")
	public String fbcreate(FreeBoardDto freeBoardDto) {
		return"fbcreate";
	}
	
	//게시판 글 등록 페이지 postMapping 03-08
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/fbcreate")
	public String fbcreate(@Valid FreeBoardDto freeBoardDto, BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "/fbcreate";
		}
		

		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.freeboardService.fbcreate(freeBoardDto.getContent(), freeBoardDto.getSubject(), siteUser);
		
		
		return "redirect:/freeboard";
		
	}
	
	

	
	
	
}
