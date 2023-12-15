package com.utez.geco.service;

import com.utez.geco.model.Hotel;
import com.utez.geco.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HotelService {
    @Autowired
    private HotelRepository hr;

    public List<Hotel> findAll() {
        return hr.findAll();
    }

    public Hotel findById(long id) {
        return hr.findByIdHotel(id);
    }

    public boolean save(Hotel hotel) {
        Hotel saved = hr.save(hotel);
        return saved != null;
    }

    public long findLastId() {
        return hr.findLastId();
    }

    public boolean update(Hotel hotel) {
        Hotel updated = hr.save(hotel);
        return updated != null;
    }
}
