package project.LSH_PJ;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 예외처리 03-08
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "요청한 레코드를 못찾았습니다.")
public class DataNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException (String message) {
		super(message);
	}
	
}
