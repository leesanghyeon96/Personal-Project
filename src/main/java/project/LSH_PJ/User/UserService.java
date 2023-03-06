package project.LSH_PJ.user;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        user.setPassword(passwordEncoder.encode(password));
        
        
        this.userRepository.save(user);
        
        return user;
        
    }
    
}