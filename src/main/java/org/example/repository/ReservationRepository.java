package org.example.repository;

import org.example.entity.Reservation;
import org.example.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM reservations r WHERE r.startDate BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationBetweenDate(@Param("startDate")LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);
    public List<Reservation> findByStatus(ReservationStatus Status);
}
