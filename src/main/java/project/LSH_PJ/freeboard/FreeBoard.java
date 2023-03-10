package project.LSH_PJ.freeboard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import project.LSH_PJ.afreeboard.Fanswer;
import project.LSH_PJ.user.SiteUser;

@Entity
@Getter
@Setter
public class FreeBoard {
	// 03-07 (자유게시판 엔티티 생성)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private SiteUser author;
	
	//03-09 답변
	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.REMOVE) //질문 삭제시 답변도 삭제
    private List<Fanswer> answerList;
	
	//글 수정 03-09
	private LocalDateTime modifyDate;
	
	//추천 03-09
	@ManyToMany
	Set<SiteUser> voter;
}
