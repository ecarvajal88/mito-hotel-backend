package com.mitocode.service.impl;

import com.mitocode.model.Reservation;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IReservationRepo;
import com.mitocode.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService extends CRUDImpl<Reservation, Integer> implements IReservationService {

    private final IReservationRepo repo;
    @Override
    protected IGenericRepo<Reservation, Integer> getRepo() {
        return repo;
    }

    @Override
    public Integer getReservationConflicts(Integer idRoom, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return repo.getReservationConflicts(idRoom, checkInDate, checkOutDate);
    }
}
