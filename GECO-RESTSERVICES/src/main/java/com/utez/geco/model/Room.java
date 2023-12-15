package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room", nullable = false, unique = true)
    private long idRoom;

    @Column(name = "room_number", nullable = false)
    private long roomNumber;

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_type_room")
    private TypeRoom idTypeRoom;

    @ManyToOne
    @JoinColumn(name = "first_id_user")
    private User firstIdUser;

    @ManyToOne
    @JoinColumn(name = "second_id_user")
    private User secondIdUser;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel idHotel;

    @OneToMany(mappedBy = "idRoom")
    @JsonIgnore
    private List<Incidence> incidences;

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeRoom getIdTypeRoom() {
        return idTypeRoom;
    }

    public void setIdTypeRoom(TypeRoom idTypeRoom) {
        this.idTypeRoom = idTypeRoom;
    }

    public User getFirstIdUser() {
        return firstIdUser;
    }

    public void setFirstIdUser(User firstIdUser) {
        this.firstIdUser = firstIdUser;
    }

    public User getSecondIdUser() {
        return secondIdUser;
    }

    public void setSecondIdUser(User secondIdUser) {
        this.secondIdUser = secondIdUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Hotel getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Hotel idHotel) {
        this.idHotel = idHotel;
    }

    public List<Incidence> getIncidences() {
        return incidences;
    }

    public void setIncidences(List<Incidence> incidences) {
        this.incidences = incidences;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", roomNumber=" + roomNumber +
                ", name='" + name + '\'' +
                ", idTypeRoom=" + idTypeRoom +
                ", firstIdUser=" + firstIdUser +
                ", secondIdUser=" + secondIdUser +
                ", status=" + status +
                ", idHotel=" + idHotel +
                ", incidences=" + incidences +
                '}';
    }
}
