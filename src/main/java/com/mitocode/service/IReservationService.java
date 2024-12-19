package com.mitocode.service;

import com.mitocode.model.Reservation;

import java.time.LocalDateTime;

public interface IReservationService extends ICRUD<Reservation, Integer> {
    Integer getReservationConflicts(Integer idRoom, LocalDateTime checkInDate, LocalDateTime checkOutDate);
}
