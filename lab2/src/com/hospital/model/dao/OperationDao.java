package com.hospital.model.dao;

import com.hospital.model.treatment.Operation;

import java.util.*;

public class OperationDao extends Dao<Operation> {
    private Map<Integer, Operation> operations = new HashMap<>();

    public OperationDao() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(currentId++, "Heart transplant"));
        operations.add(new Operation(currentId++, "Appendectomy"));
        for (Operation operation : operations) {
            this.operations.put(operation.getId(), operation);
        }
    }

    @Override
    public Operation get(int id) {
        return operations.get(id);
    }

    @Override
    public List<Operation> getAll() {
        return new ArrayList<Operation>(operations.values());
    }

    @Override
    public void save(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    @Override
    public void update(Operation operation) {
        operations.replace(operation.getId(), operation);
    }

    @Override
    public void delete(int id) {
        operations.remove(id);
    }
}