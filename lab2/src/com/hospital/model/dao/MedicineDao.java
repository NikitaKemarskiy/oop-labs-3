package com.hospital.model.dao;

import com.hospital.model.treatment.Medicine;

import java.util.*;

public class MedicineDao extends Dao<Medicine> {
    private Map<Integer, Medicine> medicines = new HashMap<>();

    public MedicineDao() {
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine(currentId++, "Karvalol"));
        medicines.add(new Medicine(currentId++, "Pentalgin"));
        for (Medicine medicine : medicines) {
            this.medicines.put(medicine.getId(), medicine);
        }
    }

    @Override
    public Medicine get(int id) {
        return medicines.get(id);
    }

    @Override
    public List<Medicine> getAll() {
        return new ArrayList<Medicine>(medicines.values());
    }

    @Override
    public void save(Medicine medicine) {
        medicines.put(medicine.getId(), medicine);
    }

    @Override
    public void update(Medicine medicine) {
        medicines.replace(medicine.getId(), medicine);
    }

    @Override
    public void delete(int id) {
        medicines.remove(id);
    }
}