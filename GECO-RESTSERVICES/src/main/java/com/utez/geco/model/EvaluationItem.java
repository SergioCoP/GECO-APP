package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "evaluation_item")
public class EvaluationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation_item", nullable = false, unique = true)
    private long idEvaluationItem;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel idHotel;

    @ManyToMany(mappedBy = "evaluationItems")
    @JsonIgnore
    private List<TypeRoom> typeRooms;

    public long getIdEvaluationItem() {
        return idEvaluationItem;
    }

    public void setIdEvaluationItem(long idEvaluationItem) {
        this.idEvaluationItem = idEvaluationItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<TypeRoom> getTypeRooms() {
        return typeRooms;
    }

    public void setTypeRooms(List<TypeRoom> typeRooms) {
        this.typeRooms = typeRooms;
    }

    @Override
    public String toString() {
        return "EvaluationItem{" +
                "idEvaluationItem=" + idEvaluationItem +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", idHotel=" + idHotel +
                ", typeRooms=" + typeRooms +
                '}';
    }
}
