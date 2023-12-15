package com.utez.geco.DTO;

public class UsrChgPassDTO {
    private long idUser;
    private String change, current, confirmation;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "UsrChgPassDTO{" +
                "idUser=" + idUser +
                ", change='" + change + '\'' +
                ", current='" + current + '\'' +
                ", confirmation='" + confirmation + '\'' +
                '}';
    }
}
