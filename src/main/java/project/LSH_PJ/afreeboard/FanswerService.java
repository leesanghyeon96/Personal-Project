package project.LSH_PJ.afreeboard;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.freeboard.FreeBoard;

//답변을 저장 03-09
@RequiredArgsConstructor
@Service
public class FanswerService {

    private final FanswerRepository fanswerRepository;

    // 답변을 저장 03-09
    public void create(FreeBoard freeBoard, String content) {
    	
    	Fanswer fanswer = new Fanswer();
    	fanswer.setContent(content);
    	fanswer.setCreateDate(LocalDateTime.now());
    	fanswer.setFreeBoard(freeBoard);
    	
    	this.fanswerRepository.save(fanswer);
    	
    	
    }
}
