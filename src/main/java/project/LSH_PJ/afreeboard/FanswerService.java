package project.LSH_PJ.afreeboard;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.DataNotFoundException;
import project.LSH_PJ.freeboard.FreeBoard;
import project.LSH_PJ.user.SiteUser;

//답변을 저장 03-09
@RequiredArgsConstructor
@Service
public class FanswerService {

    private final FanswerRepository fanswerRepository;

    // 답변을 저장 03-09
    public void create(FreeBoard freeBoard, String content, SiteUser author) {
    	
    	Fanswer fanswer = new Fanswer();
    	fanswer.setContent(content);
    	fanswer.setCreateDate(LocalDateTime.now());
    	fanswer.setFreeBoard(freeBoard);
    	
    	fanswer.setAuthor(author);
    	
    	this.fanswerRepository.save(fanswer);
    	
    }
    
    
    // 03-10 답변 조회
    public Fanswer getAnswer(Integer id) {
    	
    	//System.out.println("=====> 서비스 id : " + id);
    	
    	
        Optional<Fanswer> fanswer = this.fanswerRepository.findById(id);
        
        
        if (fanswer.isPresent()) {
            return fanswer.get();
            
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }
    
    
    //03-10 답변삭제
    public void adelete(Fanswer fanswer) {
    	this.fanswerRepository.delete(fanswer);
    }
    

}
