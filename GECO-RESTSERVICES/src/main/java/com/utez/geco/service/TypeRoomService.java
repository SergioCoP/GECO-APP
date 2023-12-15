package com.utez.geco.service;

import com.utez.geco.model.TypeRoom;
import com.utez.geco.repository.TypeRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TypeRoomService {
    @Autowired
    private TypeRoomRepository trr;

    public List<TypeRoom> findAll() {
        return trr.findAll();
    }

    public TypeRoom findById(long id) {
        return trr.findByIdTypeRoom(id);
    }

    public boolean save(TypeRoom typeRoom) {
        TypeRoom saved = trr.save(typeRoom);
        return saved != null;
    }

    public boolean update(TypeRoom typeRoom) {
        return trr.save(typeRoom) != null;
    }

    public long findLastId() {
        return trr.findLastId();
    }

    public List<TypeRoom> findByIdHotel(long idHotel) {
        return trr.findAllByIdHotel(idHotel);
    }
}
