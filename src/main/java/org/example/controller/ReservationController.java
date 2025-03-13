package org.example.controller;

import org.example.entity.Reservation;
import org.example.enums.ReservationStatus;
import org.example.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationServiceImpl reservationServiceImpl;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations(){
        return reservationServiceImpl.getAllReservations();
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        Reservation createdReservation = reservationServiceImpl.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Reservation>> getReservationByStatus(@RequestParam("status")ReservationStatus status){
        return ResponseEntity.ok(reservationServiceImpl.findByStatus(status));
    }
}
