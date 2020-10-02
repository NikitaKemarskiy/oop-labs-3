package com.hospital.model.dao;

import com.hospital.model.treatment.Procedure;

import java.util.*;

public class ProcedureDao extends Dao<Procedure> {
    private Map<Integer, Procedure> procedures = new HashMap<>();

    public ProcedureDao() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(new Procedure(currentId++, "Massage"));
        procedures.add(new Procedure(currentId++, "Cuckoo"));
        for (Procedure procedure : procedures) {
            this.procedures.put(procedure.getId(), procedure);
        }
    }

    @Override
    public Procedure get(int id) {
        return procedures.get(id);
    }

    @Override
    public List<Procedure> getAll() {
        return new ArrayList<Procedure>(procedures.values());
    }

    @Override
    public void save(Procedure procedure) {
        procedures.put(procedure.getId(), procedure);
    }

    @Override
    public void update(Procedure procedure) {
        procedures.replace(procedure.getId(), procedure);
    }

    @Override
    public void delete(int id) {
        procedures.remove(id);
    }
}