package project.LSH_PJ.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.LSH_PJ.user.SiteUser;

@Getter
@Setter
@ToString
public class ReservationDTO {
	
    private String programs;

    private String num;

    private String dday;

    private String dtime;
    
    private LocalDateTime createDate;
    
    @ManyToOne
    private SiteUser author;
}
