package project.LSH_PJ.user;

import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.DataNotFoundException;
import project.LSH_PJ.constant.Role;

@RequiredArgsConstructor
@Service
@EnableAutoConfiguration
public class UserService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    
//    public SiteUser saveUser(SiteUser siteUser) {
//    	twouser(siteUser);
//    	return userRepository.save(siteUser);
//    }
//    
//    private void twouser(SiteUser siteUser) {
//    	Optional<SiteUser> findUser = userRepository.findByusername(siteUser.getUsername());
//    	
//    	if(findUser != null) {
//    		throw new IllegalStateException("이미 가입된 회원입니다.");
//    	}
//    }
    
//    public SiteUser create(String username, String email, String password, Role role) {
//    	SiteUser user = new SiteUser();
//    	
//    	user.setUsername(username);
//    	user.setEmail(email);
//    	
//        
//        //SecurityConfig에 빈을 생성해 객체주입
//        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    	//user.setPassword(passwordEncoder.encode(password));
//        user.setPassword(this.passwordEncoder.encode(password));
//        
//        user.setRole(role);
//        
//        this.userRepository.save(user);
//        
//        return user;
//        
//    }
    
     //03-08 사용자 정보 얻어오기
    public SiteUser getUser(String email) {
    	
    	SiteUser siteUser = this.userRepository.findByUsername(email);
    	
    	if(siteUser != null) {
    		
    		//return siteUser.get();
    		return siteUser;
    		
    	}else {
    		throw new DataNotFoundException("siteuser not found");
    	}
    	
    }
    

	
//	public SiteUser getUser(String username) {
//		SiteUser siteUser = this.userRepository.findByUsername(username);
//		
//		if(siteUser != null) {
//			return siteUser.getClass();
//		}
//	}
//	
	
	
	
	
	public SiteUser saveUser(SiteUser siteUser) {
		
		return userRepository.save(siteUser);
	}
	
	private void validateDuplicateSiteUser(SiteUser siteUser) {
		Optional<SiteUser> findUser = userRepository.findById(siteUser.getId());
    	if(findUser != null) {
		throw new IllegalStateException("이미 가입된 회원입니다.");
    	}
	}
	
	
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    	SiteUser siteUser = userRepository.findByUsername(email);
    			//.findByUsername(userName);
    	
    	if(siteUser == null) {
    		throw new UsernameNotFoundException(email);
    	}
    	
    	return User.builder()
    			.username(siteUser.getUsername())
    			.password(siteUser.getPassword())
    			.roles(siteUser.getRole().toString())
    			.build();

    }



    
    
    
    
    
    
    
    
    
}