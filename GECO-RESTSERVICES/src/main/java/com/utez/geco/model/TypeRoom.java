package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_room")
public class TypeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_room", nullable = false, unique = true)
    private int idTypeRoom;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "idTypeRoom")
    @JsonIgnore
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false)
    private Hotel idHotel;

    @ManyToMany
    @JoinTable(
            name = "type_room_has_evaluation_items",
            joinColumns = @JoinColumn(name = "id_type_room"),
            inverseJoinColumns = @JoinColumn(name = "id_evaluation_item")
    )
    private List<EvaluationItem> evaluationItems;

    public int getIdTypeRoom() {
        return idTypeRoom;
    }

    public void setIdTypeRoom(int idTypeRoom) {
        this.idTypeRoom = idTypeRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Hotel idHotel) {
        this.idHotel = idHotel;
    }

    public List<EvaluationItem> getEvaluationItems() {
        return evaluationItems;
    }

    public void setEvaluationItems(List<EvaluationItem> evaluationItems) {
        this.evaluationItems = evaluationItems;
    }

    @Override
    public String toString() {
        return "TypeRoom{" +
                "idTypeRoom=" + idTypeRoom +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", idHotel=" + idHotel +
                ", evaluationItems=" + evaluationItems +
                '}';
    }
}
