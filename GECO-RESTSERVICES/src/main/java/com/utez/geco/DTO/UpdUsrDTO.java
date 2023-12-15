package com.utez.geco.DTO;

import com.utez.geco.model.Rol;

public class UpdUsrDTO {
    private long idUser;
    private Rol idRol;
    private int turn;
    private String username, email;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdUsrDTO{" +
                "idUser=" + idUser +
                ", idRol=" + idRol +
                ", turn=" + turn +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
