package project.LSH_PJ.freeboard;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import project.LSH_PJ.user.SiteUser;

public interface FreeboardRepository extends JpaRepository<FreeBoard, Integer> {
	// 03-07
	
	// 03-08 페이징처리 no.1   => service로
	Page<FreeBoard> findAll(Pageable pageable);
	
}
