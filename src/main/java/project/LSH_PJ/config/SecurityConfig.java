package project.LSH_PJ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	// 03-01 스프링 시큐리티로 인해 추가함 (로그인하지 않아도 모든 페이지 접근가능)
	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
		http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/**")).permitAll()

		.and()
			.csrf()
			.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
			
			
		.and()
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
    	
    	
    	
		// 03-10 로그인처리
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
        ;

        http.authorizeHttpRequests()
                .requestMatchers("/css/**", "/js/**", "/img/**","/header/**").permitAll()
                .requestMatchers("/", "/member/**", "/item/**", "/images/**","/login/**",
                		 "/intro/**", "/fbcreate/**", "/fbdetail/**", "/form_errors/**", "/freeboard/**",
                		 "/newg/**", "/p1_show/**", "/showroom1/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;

        return http.build();
    }
    
    // 03-01 패스워드
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 03-06 스프링 시큐리티의 인증을 담당하는 빈등록
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	

    
    
}
