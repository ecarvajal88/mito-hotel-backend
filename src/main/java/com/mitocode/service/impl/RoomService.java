package com.mitocode.service.impl;

import com.mitocode.model.Room;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRoomRepo;
import com.mitocode.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService extends CRUDImpl<Room, Integer> implements IRoomService {

    private final IRoomRepo repo;
    @Override
    protected IGenericRepo<Room, Integer> getRepo() {
        return repo;
    }
}
