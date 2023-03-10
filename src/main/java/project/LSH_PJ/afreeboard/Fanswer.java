package project.LSH_PJ.afreeboard;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import project.LSH_PJ.freeboard.FreeBoard;
import project.LSH_PJ.user.SiteUser;

@Getter
@Setter
@Entity
public class Fanswer {
	//03-09 답변
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    
    @ManyToOne
    private FreeBoard freeBoard;
    
    //글 수정 03-09
    private LocalDateTime modifyDate;
    
    //추천 03-09
    @ManyToMany
    Set<SiteUser> voter;
	
}
