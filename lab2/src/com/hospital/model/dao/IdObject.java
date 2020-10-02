package com.hospital.model.dao;

public class IdObject {
    protected int id;

    public IdObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("ID: %d", id);
    }
}
