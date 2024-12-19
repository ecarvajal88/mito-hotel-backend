package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Integer idRoom;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String number;
    @NotNull
    private String type;
    @NotNull
    private double price;
    @NotNull
    private boolean available;
}
