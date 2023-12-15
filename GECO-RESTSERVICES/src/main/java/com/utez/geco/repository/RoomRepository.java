package com.utez.geco.repository;

import com.utez.geco.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAll();
    @Query(value = "SELECT * FROM room WHERE id_hotel = :idHotel", nativeQuery = true)
    List<Room> findByIdHotel(@Param("idHotel") long idHotel);
    Room findByIdRoom(long id);
    Room save(Room room);
    @Query(value = "SELECT id_room FROM room ORDER BY id_room DESC LIMIT 1", nativeQuery = true)
    long findLastId();
    @Modifying
    @Query(value = "UPDATE room SET status = :status WHERE id_room = :id", nativeQuery = true)
    int changeStatus(@Param("status") int status, @Param("id") long idRoom);
    @Query(value = "SELECT room_number FROM room ORDER BY room_number DESC LIMIT 1", nativeQuery = true)
    long getLastRoomIndex(@Param("id") long id);
}
