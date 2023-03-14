package project.LSH_PJ.user;


import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import project.LSH_PJ.constant.Role;

// 03-01 (회원가입을 위한 엔티티 클래스 생성) : 사용자의 확인
@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    public String username;
    

    private String password;

    @Column(unique = true)
    private	String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    //03-09 권한부여
    public static SiteUser createSiteUser(UserDto userDto, 
    		PasswordEncoder passwordEncoder) {
    	SiteUser siteUser = new SiteUser();
    	siteUser.setUsername(userDto.getUsername());
    	siteUser.setEmail(userDto.getEmail());
    	String password = passwordEncoder.encode(userDto.getPassword1());
    	siteUser.setPassword(password);
    	siteUser.setRole(Role.ADMIN);
    	
    	return siteUser;
    }


}

