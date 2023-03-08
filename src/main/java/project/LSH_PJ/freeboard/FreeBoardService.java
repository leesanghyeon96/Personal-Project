package project.LSH_PJ.freeboard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.DataNotFoundException;
import project.LSH_PJ.user.SiteUser;
import project.LSH_PJ.user.UserRepository;

@Service
@RequiredArgsConstructor
public class FreeBoardService {

	private final FreeboardRepository freeboardRepository;
	private final UserRepository userRepository;
	
	// DB에 있는 레코드 가져와서 뿌려주기 03-08
	public List<FreeBoard> getfreeBoardList(){
		return this.freeboardRepository.findAll();
	}
	
	
	// 글 등록 insert 메소드 생성 03-08
	public void fbcreate(String content, String subject, SiteUser user) {
		// SiteUser user => 글쓴이
		
		FreeBoard fb = new FreeBoard();
		
		fb.setContent(content);
		fb.setSubject(subject);
		fb.setAuthor(user);
		fb.setCreateDate(LocalDateTime.now());
		
		this.freeboardRepository.save(fb);
		
	}
	
	
	// 페이징처리 메소드 생성 03-08     => controller로
	public Page<FreeBoard> getList(int page){
		
		// 페이징 순서를 역순으로 
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
		
		
		// 페이징처리 page를 10개단위로   ,   페이징 순서를 생성일자 역순으로 정렬
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		
		return this.freeboardRepository.findAll(pageable);
	}
	
	
	
	// freeboard 내용 확인을 위해 freeboard의 데이터를 조회할 메소드 생성 03-08	
	public FreeBoard getFreeBoard(Integer id) {
		
		Optional<FreeBoard> freeboard = this.freeboardRepository.findById(id);
		
		if(freeboard.isPresent()) {
			return freeboard.get();
		} else {
			throw new DataNotFoundException("값이 없습니다.");
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
