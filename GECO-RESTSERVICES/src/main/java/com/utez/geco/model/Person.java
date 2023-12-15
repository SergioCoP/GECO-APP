package com.utez.geco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id_person", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPerson;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "lastname", nullable = true)
    private String lastname;

    @OneToOne(mappedBy = "idPerson")
    @JsonIgnore
    private User user;

    public Person() {
    }

    public Person(long idPerson) {
        this.idPerson = idPerson;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", user=" + user +
                '}';
    }
}
