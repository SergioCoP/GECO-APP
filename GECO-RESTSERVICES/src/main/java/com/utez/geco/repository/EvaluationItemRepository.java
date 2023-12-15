package com.utez.geco.repository;

import com.utez.geco.model.EvaluationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationItemRepository extends JpaRepository<EvaluationItem, Long> {
    List<EvaluationItem> findAll();
    EvaluationItem save(EvaluationItem evaluationItem);
    EvaluationItem findById(long id);
    @Query(value = "SELECT * FROM evaluation_item WHERE id_hotel = :idHotel", nativeQuery = true)
    List<EvaluationItem> findByIdHotel(@Param("idHotel") long idHotel);
    @Modifying
    @Query(value = "UPDATE evaluation_item SET name = :name WHERE id_evaluation_item = :id", nativeQuery = true)
    int update(@Param("name") String name, @Param("id") long idEvaluationItem);
    @Modifying
    @Query(value = "UPDATE evaluation_item SET status = :change WHERE id_evaluation_item = :id", nativeQuery = true)
    int changeStatus(@Param("change") int change, @Param("id") long id);
    @Query(value = "SELECT id_evaluation_item FROM evaluation_item ORDER BY id_evaluation_item DESC LIMIT 1", nativeQuery = true)
    long findLastId();
}
