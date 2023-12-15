package com.utez.geco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "incidence")
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidence", nullable = false, unique = true)
    private long idIncidence;

    @Column(name = "image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "discovered_on", nullable = false)
    private String discoveredOn;

    @Column(name = "resolved_on", nullable = true)
    private String resolvedOn;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "id_iuser")
    private User idUser;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room idRoom;

    public Incidence() {
    }

    public Incidence(byte[] image, String discoveredOn, String description, int status, User idUser, Room idRoom) {
        this.image = image;
        this.discoveredOn = discoveredOn;
        this.description = description;
        this.status = status;
        this.idUser = idUser;
        this.idRoom = idRoom;
    }

    public long getIdIncidence() {
        return idIncidence;
    }

    public void setIdIncidence(long idIncidence) {
        this.idIncidence = idIncidence;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDiscoveredOn() {
        return discoveredOn;
    }

    public void setDiscoveredOn(String discoveredOn) {
        this.discoveredOn = discoveredOn;
    }

    public String getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(String resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public String toString() {
        return "Incidence{" +
                "idIncidence=" + idIncidence +
                ", image='" + image + '\'' +
                ", discoveredOn='" + discoveredOn + '\'' +
                ", resolvedOn='" + resolvedOn + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", idUser=" + idUser +
                ", idRoom=" + idRoom +
                '}';
    }
}
