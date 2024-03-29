package project.LSH_PJ.reservation;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import project.LSH_PJ.user.SiteUser;
import project.LSH_PJ.user.UserService;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	private final UserService userService;
	
	// getMapping
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/rv")
	public String rv() {
		
		return "rv";
	}
	
    @PostMapping("/rv")
    public String submitReservation(@Valid ReservationDTO reservationDTO, Model model, Principal principal) {

        SiteUser siteUser = this.userService.getUser(principal.getName());
        
        this.reservationService.resv(reservationDTO.getPrograms(), reservationDTO.getNum(), 
        		reservationDTO.getDday(), reservationDTO.getDtime(), siteUser);
        
        model.addAttribute("message", "예약이 완료되었습니다.");

        return "rvend";
    }
	
	
}
