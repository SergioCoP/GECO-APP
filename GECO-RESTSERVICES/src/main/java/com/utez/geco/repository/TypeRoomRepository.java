package com.utez.geco.repository;

import com.utez.geco.model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long> {
    List<TypeRoom> findAll();
    TypeRoom findByIdTypeRoom(long id);
    @Query(value = "SELECT * FROM type_room WHERE id_hotel = :idHotel", nativeQuery = true)
    List<TypeRoom> findAllByIdHotel(@Param("idHotel") long idHotel);
    TypeRoom save(TypeRoom typeRoom);
    @Query(value = "SELECT id_type_room FROM type_room ORDER BY id_type_room DESC LIMIT 1", nativeQuery = true)
    long findLastId();

}
