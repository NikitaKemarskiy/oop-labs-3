package com.hospital.model.dao;

import com.hospital.model.person.Nurse;
import com.hospital.util.Util;

import java.util.*;

public class NurseDao extends Dao<Nurse> {
    private Map<Integer, Nurse> nurses = new HashMap<>();

    public NurseDao() {
        List<Nurse> nurses = new ArrayList<>();
        nurses.add(new Nurse(currentId++, "Zhenya", "Gorschik", Util.getRandomDate()));
        nurses.add(new Nurse(currentId++, "Max", "Pugachov", Util.getRandomDate()));
        for (Nurse nurse : nurses) {
            this.nurses.put(nurse.getId(), nurse);
        }
    }

    @Override
    public Nurse get(int id) {
        return nurses.get(id);
    }

    @Override
    public List<Nurse> getAll() {
        return new ArrayList<Nurse>(nurses.values());
    }

    @Override
    public void save(Nurse nurse) {
        nurses.put(nurse.getId(), nurse);
    }

    @Override
    public void update(Nurse nurse) {
        nurses.replace(nurse.getId(), nurse);
    }

    @Override
    public void delete(int id) {
        nurses.remove(id);
    }
}