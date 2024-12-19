package com.mitocode.repo;

import com.mitocode.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IReservationRepo extends IGenericRepo<Reservation, Integer> {
    @Query(value = "SELECT COUNT(*) FROM reservation WHERE id_room = :idRoom AND (:checkInDate BETWEEN check_in_date AND check_out_date OR :checkOutDate BETWEEN check_in_date AND check_out_date OR (:checkInDate = check_in_date AND :checkOutDate = check_out_date))", nativeQuery = true)
    Integer getReservationConflicts(@Param("idRoom") Integer idRoom, @Param("checkInDate") LocalDateTime checkInDate, @Param("checkOutDate") LocalDateTime checkOutDate);
}
