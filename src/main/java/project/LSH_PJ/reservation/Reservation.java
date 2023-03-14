package project.LSH_PJ.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.LSH_PJ.user.SiteUser;

@Entity
@Getter
@Setter
@ToString
public class Reservation {
	// 03-13
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String programs;

    private String num;

    private String dday;

    private String dtime;
    
    private LocalDateTime createDate;
    
    @ManyToOne
    private SiteUser author;
    
}