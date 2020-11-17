package com.nikita.model.dao;

import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.Patient;

import java.util.List;

public interface PatientDao extends GenericDao<Patient> {
    List<Patient> findAllWithRelations();
    List<Patient> findByDoctorsIds(int[] doctorsIds);
    List<Patient> findByDoctorsIdsWithRelations(int id);
    void updatePatientDoctor(int id, int doctorId);
}
