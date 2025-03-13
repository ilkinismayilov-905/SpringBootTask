package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Reservation;
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
public class ReservationServiceImpl {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = new ArrayList<>();

        for (Reservation reservation:reservationRepository.findAll()){
            reservationList.add(reservation);
        }
        if(reservationList.isEmpty()){
            throw new EmptyListExcepption("Reservation List Is Empty");

        }        return reservationList;
    }

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByStatus(ReservationStatus status){
        return reservationRepository.findByStatus(status);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void removeExpiredReservations(){
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findReservationBetweenDate(now.minusDays(1),now);
        reservationRepository.deleteAll(expiredReservations);
    }


//    @Override
//    public void deleteById(Object id) {
//        reservationRepository.deleteById((Long) id);
//    }
//
//    @Override
//    public Object save(Object entity) {
//        return reservationRepository.save(entity);
//    }
//
//    @Override
//    public Optional<T> getById(Object id) {
//        Optional<T> reserv = reservationRepository.findById(id);
//
//        if(reserv.isEmpty()){
//            throw  new NotFoundByIdException();
//        }
//        return reserv;
//    }
//
//    @Override
//    public List<T> getAll() {
//        List<T> reservationList = new ArrayList<>();
//        for(Object reservation:reservationRepository.findAll()){
//            reservationList.add((T) reservation);
//        }
//        return reservationList;
//    }
}
