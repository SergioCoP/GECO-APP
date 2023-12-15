package com.utez.geco.DTO;

public class RmChgSttsDTO {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RmChgSttsDTO{" +
                "status=" + status +
                '}';
    }
}
