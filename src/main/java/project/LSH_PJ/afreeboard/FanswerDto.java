package project.LSH_PJ.afreeboard;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FanswerDto {
    @NotEmpty(message = "내용을 입력해야만 답변을 등록할 수 있습니다.")
    private String content;
}
