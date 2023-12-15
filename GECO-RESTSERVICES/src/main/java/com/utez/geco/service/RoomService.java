package com.utez.geco.service;

import com.utez.geco.model.Room;
import com.utez.geco.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository rr;

    public List<Room> findAll() {
        return rr.findAll();
    }

    public List<Room> findByHotel(long idHotel) {
        return rr.findByIdHotel(idHotel);
    }

    public Room findById(long id) {
        return rr.findByIdRoom(id);
    }

    public boolean save(Room room) {
        return rr.save(room) != null;
    }

    public boolean update(Room room) {
        return rr.save(room) != null;
    }

    public long returnLastId() {
        return rr.findLastId();
    }

    public boolean changeStatus(int status, long idRoom) {
        return rr.changeStatus(status, idRoom) != 0;
    }

    public long getLastRoomIndex(long id) {
        return rr.getLastRoomIndex(id);
    }
}
