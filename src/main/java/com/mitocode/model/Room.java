package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoom;

    @Column(length = 10, nullable = false, unique = true)
    private String number;

    @Column(length = 30, nullable = false)
    private String type;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private double price;

    @Column(nullable = false)
    private boolean available;
}
