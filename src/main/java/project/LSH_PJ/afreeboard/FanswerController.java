package project.LSH_PJ.afreeboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.LSH_PJ.freeboard.FreeBoard;
import project.LSH_PJ.freeboard.FreeBoardService;

//답변 컨트롤러
@RequiredArgsConstructor
@Controller
public class FanswerController {
	
//    private final FreeBoardService freeBoardService;
//    private final FanswerService fanswerService;
//    
//    // 상세 게시판답변을 저장 03-09
//    @PostMapping("/facreate/{id}")
//    public String fbcreate(Model model, @PathVariable("id") Integer id, 
//    		@Valid FanswerDto FanswerDto, BindingResult bindingResult) {
//    	
//        FreeBoard freeBoard = this.freeBoardService.getFreeBoard(id);
//        
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("FreeBoard", freeBoard);
//            return "fbdetail";
//        }
//        
//        
//        this.fanswerService.create(freeBoard, FanswerDto.getContent());
//        
//        return String.format("redirect:fbcreate/%s", id);
//    }
	
	
    private final FreeBoardService freeBoardService;
    private final FanswerService fanswerService;

    @PostMapping("/facreate/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
    	FreeBoard freeBoard = this.freeBoardService.getFreeBoard(id);
    	
    	this.fanswerService.create(freeBoard, content);
        return String.format("redirect:/fbdetail/%s", id);
    }

    

    
}
