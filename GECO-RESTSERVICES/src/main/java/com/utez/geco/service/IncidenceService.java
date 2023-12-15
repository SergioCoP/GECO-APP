package com.utez.geco.service;

import com.utez.geco.model.Incidence;
import com.utez.geco.repository.IncidenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class IncidenceService {
    @Autowired
    private IncidenceRepository ir;

    public List<Incidence> findAll() {
        return ir.findAll();
    }

    public Incidence findById(long id) {
        return ir.findByIdIncidence(id);
    }

    public boolean save(Incidence incidence) {
        return ir.save(incidence) != null;
    }

    public List<Incidence> findByRoom(long idRoom) {
        return ir.findByIdRoom(idRoom);
    }

    public List<Incidence> findByHotel(long idHotel) {
        return ir.findByIdHotel(idHotel);
    }

    public boolean changeStatus(String solved, long id) {
        return ir.changeStatus(solved, id) != 0;
    }
}
