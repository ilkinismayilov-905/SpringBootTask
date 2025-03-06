package org.example.controller;

import jakarta.validation.constraints.Positive;
import org.example.entity.Reservation;
import org.example.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        reservationService.createReservation(reservation);
        return ResponseEntity.ok().build();
    }
}
