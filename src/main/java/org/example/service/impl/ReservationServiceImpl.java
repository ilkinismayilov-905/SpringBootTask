package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Customer;
import org.example.entity.Reservation;
import org.example.entity.User;
import org.example.enums.ReservationStatus;
import org.example.exceptions.EmptyListExcepption;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.ReservationRepository;
import org.example.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

//    public List<Reservation> getAllReservations(){
//        List<Reservation> reservationList = new ArrayList<>();
//
//        for (Reservation reservation:reservationRepository.findAll()){
//            reservationList.add(reservation);
//        }
//        if(reservationList.isEmpty()){
//            throw new EmptyListExcepption("Reservation List Is Empty");
//
//        }        return reservationList;
//    }
//
//    public Reservation createReservation(Reservation reservation){
//        return reservationRepository.save(reservation);
//    }
//
    public List<Reservation> findByStatus(ReservationStatus status){
        return reservationRepository.findByStatus(status);
    }
//
//    public void deleteReservationById(Long id){
//        Optional<Reservation> reservation = reservationRepository.findById(id);
//        if(reservation.isPresent()){
//            reservationRepository.deleteById(id);
//        }
//        throw new NotFoundByIdException();
//    }

    @Scheduled(cron = "0 0 0 * * *")
    public void removeExpiredReservations(){
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findReservationBetweenDate(now.minusDays(1),now);
        reservationRepository.deleteAll(expiredReservations);
    }

    @Override
    public void deleteById(Long id) {
        if(reservationRepository.existsById(id)){
            reservationRepository.deleteById(id);
        }
        throw new NotFoundByIdException();
    }

    @Override
    public Reservation save(Reservation entity) {
        return reservationRepository.save(entity);
    }

    @Override
    public Optional<Reservation> getById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
           return reservationRepository.findById(id);
        }
        throw new NotFoundByIdException();
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }


}
