package com.hospital.model.dao;

import java.util.List;
import java.util.Optional;

public abstract class Dao<T extends IdObject> {
    protected int currentId;

    public Dao() {
        currentId = 1;
    }

    public abstract T get(int id);
    public abstract List<T> getAll();
    public abstract void save(T t);
    public abstract void update(T t);
    public abstract void delete(int id);

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
}