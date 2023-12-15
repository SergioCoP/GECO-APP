package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel", nullable = false, unique = true)
    private long idHotel;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "primaryColor", nullable = false)
    private String primaryColor;

    @Column(name = "secondaryColor", nullable = false)
    private String secondaryColor;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "idHotel")
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "idHotel")
    @JsonIgnore
    private List<Room> rooms;

    @OneToMany(mappedBy = "idHotel")
    @JsonIgnore
    private List<TypeRoom> typeRooms;

    public Hotel() {
    }

    public Hotel(long idHotel) {
        this.idHotel = idHotel;
    }

    public long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(long idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<TypeRoom> getTypeRooms() {
        return typeRooms;
    }

    public void setTypeRooms(List<TypeRoom> typeRooms) {
        this.typeRooms = typeRooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", name='" + name + '\'' +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", users=" + users +
                ", rooms=" + rooms +
                ", typeRooms=" + typeRooms +
                '}';
    }
}
