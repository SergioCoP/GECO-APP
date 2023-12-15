package com.utez.geco.DTO;

import com.utez.geco.model.Room;
import com.utez.geco.model.User;

public class SvIncDTO {
    private String image;
    private String description;
    private User idUser;
    private Room idRoom;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "SvIncDTO{" +
                "image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", idUser=" + idUser +
                ", idRoom=" + idRoom +
                '}';
    }
}