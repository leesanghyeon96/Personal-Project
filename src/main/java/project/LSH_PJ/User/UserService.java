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
    


	
    // 03-10
	public SiteUser saveUser(SiteUser siteUser) {
		
		return userRepository.save(siteUser);
	}
	
	private void validateDuplicateSiteUser(SiteUser siteUser) {
		Optional<SiteUser> findUser = userRepository.findById(siteUser.getId());
    	if(findUser != null) {
		throw new IllegalStateException("이미 가입된 회원입니다.");
    	}
	}
	
	// 03-10
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    	SiteUser siteUser = userRepository.findByUsername(email);
    	
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