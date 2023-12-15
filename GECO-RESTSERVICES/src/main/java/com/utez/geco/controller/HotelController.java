package com.utez.geco.controller;

import com.utez.geco.model.Hotel;
import com.utez.geco.service.HotelService;
import com.utez.geco.utils.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(
        origins = "http://52.1.80.209:3000",
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST},
        allowedHeaders = {HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION},
        exposedHeaders = {HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION}
)
@RestController
@RequestMapping(value = "/api/hotel",method = RequestMethod.OPTIONS)
public class HotelController {
    @Autowired
    private HotelService hs;
    private CustomService cs = new CustomService();
    private HashMap<String, Object> response;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        response = new HashMap<>();
        List<Hotel> hotelList = hs.findAll();

        if(hotelList.isEmpty()) {
            response.put("status", HttpStatus.OK);
            response.put("message", "Aún no hay registros");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", HttpStatus.OK);
        response.put("message", "Operación exitosa");
        response.put("data", hotelList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        response = new HashMap<>();
        Hotel found = hs.findById(id);

        if(found == null) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "No se encontró el registro");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", HttpStatus.OK);
        response.put("message", "Se encontró el registro");
        response.put("data", found);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Hotel hotel) {
        response = new HashMap<>();
        if(!cs.checkBlacklists(hotel.toString())) {
            if(hs.save(hotel)) {
                long idHotel = hs.findLastId();
                response.put("status", HttpStatus.CREATED);
                response.put("idPerson", idHotel);
                response.put("message", "Se creó el registro");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }

        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "No se creó el registro");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Hotel hotel) {
        response = new HashMap<>();
        if(hs.findById(hotel.getIdHotel()) != null) {
            if(!cs.checkBlacklists(hotel.toString())) {
                if(hs.save(hotel)) {
                    response.put("status", HttpStatus.OK);
                    response.put("message", "Se modificó el registro");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } else {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "No se encontró el registro");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "No se creó el registro");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
