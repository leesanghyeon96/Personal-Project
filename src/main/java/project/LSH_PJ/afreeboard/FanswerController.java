package project.LSH_PJ.afreeboard;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

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
import project.LSH_PJ.DataNotFoundException;
import project.LSH_PJ.freeboard.FreeBoard;
import project.LSH_PJ.freeboard.FreeBoardService;
import project.LSH_PJ.user.SiteUser;
import project.LSH_PJ.user.UserService;

//답변 컨트롤러
@RequiredArgsConstructor
@Controller
public class FanswerController {

    private final FreeBoardService freeBoardService;
    private final FanswerService fanswerService;
    private final UserService userService;
    
    // 상세 게시판답변을 저장 03-09
    @PostMapping("/facreate/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, 
    		@Valid FanswerDto fanswerDto, BindingResult bindingResult, Principal principal) {
    	
    		//System.out.println(principal.getName());
    	
    	FreeBoard freeBoard = this.freeBoardService.getFreeBoard(id);
    	//답변을 저장하는 메소드 호출
    	
    	SiteUser siteUser = this.userService.getUser(principal.getName());
    		//principal.getName(): 현재 로그인한 사용자확인
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("FreeBoard", freeBoard);
    		return "fbdetail";
    	}
    	
    	this.fanswerService.create(freeBoard, fanswerDto.getContent(), siteUser);
    	
        return String.format("redirect:/fbdetail/%s", id);
        
    }

    
    
    //댓글 삭제기능 03-10
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/adelete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
    	
    	//System.out.println("=====> : " + id);
        Fanswer fanswer = this.fanswerService.getAnswer(id);
        
        
        
        if (!fanswer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        
        this.fanswerService.adelete(fanswer);
        return String.format("redirect:/fbdetail/%s", fanswer.getFreeBoard().getId());
    }
    

    
}
