package project.LSH_PJ.freeboard;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

//03-08 게시판 입력
@Getter
@Setter
public class FreeBoardDto {
	
	@NotEmpty(message = "제목을 입력해주세요.")
	private String subject;
	
	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;
}
