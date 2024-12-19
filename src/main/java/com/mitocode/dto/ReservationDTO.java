package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Integer idReservation;
    @NotNull
    @Size(min = 5, max = 100)
    private String customerName;
    @NotNull
    private LocalDateTime checkInDate;
    @NotNull
    private LocalDateTime checkOutDate;
    @NotNull
    private RoomDTO room;
}
