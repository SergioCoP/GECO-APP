package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id_user", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "turn", nullable = true)
    private int turn;

    @Column(name = "status", nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name = "id_person", nullable = false)
    private Person idPerson;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol idRol;

    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false)
    private Hotel idHotel;

    @OneToMany(mappedBy = "idUser")
    @JsonIgnore
    private List<Incidence> incidences;

    @OneToMany(mappedBy = "firstIdUser")
    @JsonIgnore
    private List<Room> r1;

    @OneToMany(mappedBy = "secondIdUser")
    @JsonIgnore
    private List<Room> r2;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
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

    public List<Room> getR1() {
        return r1;
    }

    public void setR1(List<Room> r1) {
        this.r1 = r1;
    }

    public List<Room> getR2() {
        return r2;
    }

    public void setR2(List<Room> r2) {
        this.r2 = r2;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", turn=" + turn +
                ", status=" + status +
                ", idPerson=" + idPerson +
                ", idRol=" + idRol +
                ", idHotel=" + idHotel +
                ", incidences=" + incidences +
                ", r1=" + r1 +
                ", r2=" + r2 +
                '}';
    }
}
