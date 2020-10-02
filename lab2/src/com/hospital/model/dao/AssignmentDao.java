package com.hospital.model.dao;

import com.hospital.model.treatment.Assignment;

import java.util.*;

public class AssignmentDao extends Dao<Assignment> {
    private Map<Integer, Assignment> assignments = new HashMap<>();

    public AssignmentDao() {}

    @Override
    public Assignment get(int id) {
        return assignments.get(id);
    }

    @Override
    public List<Assignment> getAll() {
        return new ArrayList<>(assignments.values());
    }

    @Override
    public void save(Assignment assignment) {
        assignments.put(assignment.getId(), assignment);
    }

    @Override
    public void update(Assignment assignment) {
        assignments.replace(assignment.getId(), assignment);
    }

    @Override
    public void delete(int id) {
        assignments.remove(id);
    }
}