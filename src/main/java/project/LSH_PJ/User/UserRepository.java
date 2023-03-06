package project.LSH_PJ.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	// 03-01 (레파지토리 추가)
	
	// 03-06 (사용자 조회 기능 추가)
	Optional<SiteUser> findByusername(String username);
	
	
}
