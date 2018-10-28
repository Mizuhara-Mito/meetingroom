package com.mtr.web;

import com.mtr.Repository.ReservationRepository;
import com.mtr.Repository.RoomRepository;
import com.mtr.domain.Reservation;
import com.mtr.domain.Room;
import com.mtr.domain.SystemMessage;
import com.mtr.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @RequestMapping("/rooms")
    public List retrieveRooms() throws Exception {
        logger.debug("============================> %s", "controller");
        return reservationService.retrieveRooms();
    }

    @RequestMapping("/reserve")
    public List<SystemMessage> reserve(@RequestBody Reservation reservation) throws Exception {
        logger.debug("============================> %s", "controller");
        return reservationService.reserve(reservation);
    }

    @RequestMapping("/retrieveReservation/{reservDt}")
    public List<Reservation> retrieveReservation(@PathVariable String reservDt) throws Exception {
        logger.debug("============================> %s", "controller");
        return reservationService.retrieveReservation(reservDt);
    }
}
