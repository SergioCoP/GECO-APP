package com.utez.geco.controller;

import com.utez.geco.model.EvaluationItem;
import com.utez.geco.service.EvaluationItemService;
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
@RequestMapping(value = "/api/evaluation-item",method = RequestMethod.OPTIONS)
public class EvaluationItemController {
    @Autowired
    private EvaluationItemService eis;
    @Autowired
    private HotelService hs;
    private CustomService cs = new CustomService();
    private HashMap<String, Object> response;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        response = new HashMap<>();
        List<EvaluationItem> evaluationItemList = eis.findAll();

        if(evaluationItemList.isEmpty()) {
            response.put("status", HttpStatus.OK);
            response.put("message", "Aún no hay registros");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", HttpStatus.OK);
        response.put("message", "Operación exitosa");
        response.put("data", evaluationItemList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        response = new HashMap<>();
        EvaluationItem found = eis.findById(id);

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

    @GetMapping("/hotel/{id}")
    public ResponseEntity<?> findByIdHotel(@PathVariable(name = "id") long id) {
        response = new HashMap<>();
        if(hs.findById(id) == null) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "No se encontró el hotel");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<EvaluationItem> evaluationItemList = eis.findByIdHotel(id);

        if(evaluationItemList.isEmpty()) {
            response.put("status", HttpStatus.OK);
            response.put("message", "Aún no hay registros");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", HttpStatus.OK);
            response.put("message", "Operación exitosa");
            response.put("data", evaluationItemList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody EvaluationItem evaluationItem) {
        response = new HashMap<>();
        evaluationItem.setStatus(1);
        if(!cs.checkBlacklists(evaluationItem.toString())) {
            if (hs.findById(evaluationItem.getIdHotel().getIdHotel()) != null) {
                if(eis.save(evaluationItem)) {
                    long idPerson = eis.findLast();
                    response.put("status", HttpStatus.CREATED);
                    response.put("idEvaluationItem", idPerson);
                    response.put("message", "Se creó el registro");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
            } else {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("message", "No se encontró el hotel");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }

        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "No se creó el registro");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody EvaluationItem evaluationItem) {
        response = new HashMap<>();
        if(!cs.checkBlacklists(evaluationItem.toString())) {
            if(eis.findById(evaluationItem.getIdEvaluationItem()) != null) {
                if(eis.update(evaluationItem)) {
                    response.put("status", HttpStatus.OK);
                    response.put("message", "Se modificó el registro");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("message", "No se encontró el registro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }

        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "No se modificó el registro");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/status/{id}")
    private ResponseEntity<?> changeStatus(@PathVariable("id") long id) {
        response = new HashMap<>();
        EvaluationItem found = eis.findById(id);
        if(found != null) {
            if(eis.changeStatus(found)) {
                response.put("status", HttpStatus.OK);
                response.put("message", "Se cambió el estado del rubro");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "No se encontró el registro");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "No se modificó el registro");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
