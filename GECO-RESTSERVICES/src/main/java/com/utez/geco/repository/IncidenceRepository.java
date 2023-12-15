package com.utez.geco.repository;

import com.utez.geco.model.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {
    List<Incidence> findAll();
    Incidence findByIdIncidence(long id);
    Incidence save(Incidence incidence);
    @Query(value = "SELECT * FROM incidence WHERE id_room = :id", nativeQuery = true)
    List<Incidence> findByIdRoom(@Param("id") long id);
    @Query(value = "SELECT i.* FROM incidence i JOIN room r ON r.id_room = i.id_room WHERE r.id_hotel = :idHotel", nativeQuery = true)
    List<Incidence> findByIdHotel(@Param("idHotel") long id);
    @Modifying
    @Query(value = "UPDATE incidence SET status = 1, resolved_on = :solved WHERE id_incidence = :id", nativeQuery = true)
    int changeStatus(@Param("solved") String solved, @Param("id") long id);
}
