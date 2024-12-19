package com.mitocode.controller;

import com.mitocode.dto.ReservationDTO;
import com.mitocode.model.Reservation;
import com.mitocode.model.Room;
import com.mitocode.service.IReservationService;
import com.mitocode.service.IRoomService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservationService service;
    private final IRoomService roomService;
    private final MapperUtil mapper;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll(){
        List<ReservationDTO> list = mapper.mapList(service.findAll(), ReservationDTO.class);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable("id") Integer id){
        Reservation obj = service.findById(id);
        return ResponseEntity.ok(mapper.map(obj, ReservationDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReservationDTO dto) throws Exception{
        Reservation obj = mapper.map(dto, Reservation.class);
        validate(obj);
        obj = service.save(mapper.map(dto, Reservation.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReservation()).toUri();
        return ResponseEntity.created(location).build();
    }

    private void validate(Reservation obj) throws Exception{
        if (obj.getCheckOutDate().isBefore(obj.getCheckInDate())){
            throw new Exception("La fecha de salida no puede ser anterior a la de entrada");
        }

        Room room = roomService.findById(obj.getRoom().getIdRoom());
        if (!room.isAvailable()){
            throw new Exception("La habitacion no esta disponible");
        }

        if (service.getReservationConflicts(obj.getRoom().getIdRoom(), obj.getCheckInDate(), obj.getCheckOutDate()) > 0){
            throw new Exception("La habitacion no esta disponible para las fechas seleccionadas");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
