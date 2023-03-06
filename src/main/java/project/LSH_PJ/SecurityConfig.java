package project.LSH_PJ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 03-01 스프링 시큐리티로 인해 추가함 (로그인하지 않아도 모든 페이지 접근가능)
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers(
				new AntPathRequestMatcher("/**")).permitAll()
		
		// 03-01 추가 (CSRF 처리시 h2-console은 예외)
        .and()
        	.csrf().ignoringRequestMatchers(
                new AntPathRequestMatcher("/h2-console/**"))
		// 03-01 추가 (화면 깨짐 오류 수정)
        .and()
            .headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
        	
        // 03-06 추가 (로그인 URL 등록)
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
        // 03-06 (로그아웃 기능 추가)
        .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        	
		
		
		;
		
		return http.build();
	}
	
	// 03-01 빈등록
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 03-06 스프링 시큐리티의 인증을 담당하는 빈등록
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    	return authenticationConfiguration.getAuthenticationManager();
    }
	
}
