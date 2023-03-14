package project.LSH_PJ.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.LSH_PJ.freeboard.FreeboardRepository;
import project.LSH_PJ.user.SiteUser;
import project.LSH_PJ.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	
	// DB에 예약 저장
	public void resv(String programs, String num, String dday, String dtime,
			 SiteUser user) {
		
		Reservation rv = new Reservation();
		
		rv.setDday(dday);
		rv.setNum(num);
		rv.setPrograms(programs);
		rv.setDtime(dtime);
		rv.setCreateDate(LocalDateTime.now());
		rv.setAuthor(user);
		
		this.reservationRepository.save(rv);
		
	}
	
	// 모든 예약 가져오기
	public List<Reservation> getreservatoinList(){
		return this.reservationRepository.findAll();
	}
	
	
}
