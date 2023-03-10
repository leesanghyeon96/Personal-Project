package project.LSH_PJ.freeboard;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.This;
import project.LSH_PJ.afreeboard.FanswerDto;
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
	public String fbdetail(Model model, @PathVariable("id") Integer id, FanswerDto fanswerDto) {
		
		//service에서 조회한 객체를 가져와 모델로 담기
		FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
		model.addAttribute("FreeBoard", freeBoard);
		
		
		return "fbdetail";
	}
	
	
	
	//게시판 글 등록 페이지 getMapping 03-08
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/fbcreate")
	public String fbcreate(FreeBoardDto freeBoardDto) {
						// th:object에 의해 FreeBoardDto 객체가 필요하다.
		
		return"fbcreate";
	}
	
	//게시판 글 등록 페이지 postMapping 03-08
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/fbcreate")
	public String fbcreate(@Valid FreeBoardDto freeBoardDto, BindingResult bindingResult, Principal principal) {
		
		if (bindingResult.hasErrors()) {
			 return "fbcreate";
		 }
			
		System.out.println(freeBoardDto.getContent());
		System.out.println(freeBoardDto.getSubject()); 

		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.freeboardService.fbcreate(freeBoardDto.getContent(), freeBoardDto.getSubject(), siteUser);
		
		
		return "redirect:/freeboard";
		
	}
	
	// 게시판 글 수정 버튼 03-09
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String Modify(FreeBoardDto freeBoardDto, @PathVariable("id") Integer id, Principal principal) {
		
		FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
		
		if(!freeBoard.getAuthor().getUsername().equals(principal.getName())) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		
		freeBoardDto.setSubject(freeBoard.getSubject());
		freeBoardDto.setContent(freeBoard.getContent());
		
		return "fbcreate";
	}
	
	// 게시판 글 수정 버튼 03-09
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String Modify(@Valid FreeBoardDto freeBoardDto, BindingResult bindingResult, 
		Principal principal, @PathVariable("id") Integer id) {
		
			if (bindingResult.hasErrors()) {
			    return "fbcreate";
			}
			
		FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
			if (!freeBoard.getAuthor().getUsername().equals(principal.getName())) {
			    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
			}
			
		this.freeboardService.modify(freeBoard, freeBoardDto.getContent(), freeBoardDto.getSubject());
		
		return String.format("redirect:/fbdetail/%s", id);
	}
	
	
	
	// 게시판 삭제 버튼 03-09
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
    	FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
    	
    	if(!freeBoard.getAuthor().getUsername().equals(principal.getName())) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
    	}
    	this.freeboardService.delete(freeBoard);
    	return "redirect:/";
    	
    }
	
	
	
	// 게시판 추천 버튼 03-09
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
    	
        FreeBoard freeBoard = this.freeboardService.getFreeBoard(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        
        this.freeboardService.vote(freeBoard, siteUser);
        
        return String.format("redirect:/fbdetail/%s", id);
    }
	
	
	
	
}
