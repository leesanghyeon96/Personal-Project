package project.LSH_PJ.user;

import lombok.Getter;

@Getter
public enum UserRole {
	// 03-06 (사용자 권한 부여)
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value;
	
}
