package com.hospital.model.dao;

import com.hospital.model.person.Patient;
import com.hospital.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class PatientDao extends Dao<Patient> {
    private Map<Integer, Patient> patients = new HashMap<>();

    public PatientDao(DoctorDao doctorDao) {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(currentId++, "Kate", "Gricaenko", doctorDao.get(1), Util.getRandomDate()));
        patients.add(new Patient(currentId++, "Mariana", "Dzividzinska", doctorDao.get(2), Util.getRandomDate()));
        for (Patient patient : patients) {
            this.patients.put(patient.getId(), patient);
        }
    }

    @Override
    public Patient get(int id) {
        return patients.get(id);
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<Patient>(patients.values());
    }

    @Override
    public void save(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    @Override
    public void update(Patient patient) {
        patients.replace(patient.getId(), patient);
    }

    @Override
    public void delete(int id) {
        patients.remove(id);
    }

    public List<Patient> getByDoctorId(int id) {
        return patients.values()
            .stream()
            .filter(patient -> patient.getId() == id)
            .collect(Collectors.toList());
    }
}