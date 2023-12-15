package com.utez.geco.service;

import com.utez.geco.model.EvaluationItem;
import com.utez.geco.repository.EvaluationItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EvaluationItemService {
    @Autowired
    private EvaluationItemRepository eir;

    public List<EvaluationItem> findAll() {
        return eir.findAll();
    }

    public EvaluationItem findById(long id) {
        return eir.findById(id);
    }

    public List<EvaluationItem> findByIdHotel(long idHotel) {
        return eir.findByIdHotel(idHotel);
    }

    public boolean save(EvaluationItem evaluationItem) {
        return eir.save(evaluationItem) != null;
    }

    public boolean update(EvaluationItem evaluationItem) {
        return eir.update(evaluationItem.getName(), evaluationItem.getIdEvaluationItem()) != 0;
    }

    public boolean changeStatus(EvaluationItem evaluationItem) {
        int change = evaluationItem.getStatus() == 1 ? 0 : 1;
        return eir.changeStatus(change, evaluationItem.getIdEvaluationItem()) != 0;
    }

    public long findLast() {
        return eir.findLastId();
    }
}
