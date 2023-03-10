package project.LSH_PJ.freeboard;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//03-08 게시판 입력
@Getter
@Setter
public class FreeBoardDto {
		
	@NotEmpty(message="제목은 필수항목입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용은 필수항목입니다.")
	private String content;
}
