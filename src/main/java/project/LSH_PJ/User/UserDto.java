package project.LSH_PJ.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// 03-01 (회원가입을 위한 DTO 생성)
@Getter
@Setter
public class UserDto {
	@Size(min=3, max=10)
	@NotEmpty(message = "사용자ID를 입력해 주세요.")
	private String username;
	
	@NotEmpty(message = "비밀번호를 입력해 주세요.")
	@Length(min=4, max=16, message = "비밀번호는 4자 이상 16자 이하로 입력해주세요.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인을 입력해 주세요.")
	private String password2;
	
	@NotEmpty(message = "이메일을 입력해 주세요.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
}
