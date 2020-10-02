package com.hospital.model.treatment;

import com.hospital.model.dao.IdObject;

public abstract class Treatment extends IdObject {
    private final String name;

    public Treatment(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
