package com.mitocode.controller;

import com.mitocode.dto.RoomDTO;
import com.mitocode.model.Room;
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
@RequestMapping("/rooms")
public class RoomController {

    private final IRoomService service;
    private final MapperUtil mapper;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll(){
        List<RoomDTO> list = mapper.mapList(service.findAll(), RoomDTO.class);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable("id") Integer id){
        Room obj = service.findById(id);
        return ResponseEntity.ok(mapper.map(obj, RoomDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RoomDTO dto){
        Room obj = service.save(mapper.map(dto, Room.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRoom()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody RoomDTO dto) throws Exception{
        Room obj = service.update(id, mapper.map(dto, Room.class));
        return ResponseEntity.ok(mapper.map(obj, RoomDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
