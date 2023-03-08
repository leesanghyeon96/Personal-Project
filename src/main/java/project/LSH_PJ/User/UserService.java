package project.LSH_PJ.user;

import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.DataNotFoundException;

@RequiredArgsConstructor
@Service
@EnableAutoConfiguration
public class UserService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
    	SiteUser user = new SiteUser();
    	
    	user.setUsername(username);
    	user.setEmail(email);
    	
        
        //SecurityConfig에 빈을 생성해 객체주입
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	//user.setPassword(passwordEncoder.encode(password));
        user.setPassword(this.passwordEncoder.encode(password));
    	
        this.userRepository.save(user);
        
        return user;
        
    }
    
    // 03-08 사용자 정보 얻어오기
    public SiteUser getUser(String username) {
    	
    	Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
    	
    	if(siteUser.isPresent()) {
    		return siteUser.get();
    	}else {
    		throw new DataNotFoundException("siteuser not found");
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
}