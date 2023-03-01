package project.LSH_PJ.User;

import org.springframework.data.jpa.repository.JpaRepository;

// 03-01 (레파지토리 추가)
public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	
}
