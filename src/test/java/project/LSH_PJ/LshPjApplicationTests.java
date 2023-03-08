package project.LSH_PJ;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.LSH_PJ.freeboard.FreeBoard;
import project.LSH_PJ.freeboard.FreeboardRepository;

@SpringBootTest
class LshPjApplicationTests {

	@Autowired
	private FreeboardRepository freeboardRepository;
	
	//03-08 게시판 DB 값 넣기 테스트
	@Test
	void contextLoads() {
		
		FreeBoard f = new FreeBoard();
		f.setContent("내용2");
		f.setSubject("제목2");
		f.setCreateDate(LocalDateTime.now());
		this.freeboardRepository.save(f);
		

	}

}
