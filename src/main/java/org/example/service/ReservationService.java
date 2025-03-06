package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Reservation;
import org.example.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = new ArrayList<>();
        for(Reservation reservation:reservationRepository.findAll()){
            reservationList.add(reservation);
        }
        return reservationList;
    }

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
}
