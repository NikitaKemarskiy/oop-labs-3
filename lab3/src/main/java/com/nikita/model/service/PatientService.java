package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.Patient;
import com.nikita.model.enums.SortType;
import com.nikita.model.util.SortUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class PatientService {
    private DaoFactory daoFactory;

    public PatientService() {
        daoFactory = new JDBCDaoFactory();
    }

    public List<Patient> getPatientsWithRelations() {
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            return patientDao.findAllWithRelations();
        }
    }

    public List<Patient> getPatientsByDoctorsIds(int[] doctorsIds) {
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            return patientDao.findByDoctorsIds(doctorsIds);
        }
    }

    public List<Patient> getPatientsByDoctorIdWithRelations(int doctorsId) {
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            return patientDao.findByDoctorsIdsWithRelations(doctorsId);
        }
    }

    public void createPatient(String name, String surname, LocalDate birthday, int doctorId) {
        Patient patient = Patient.builder()
            .name(name)
            .surname(surname)
            .birthday(birthday)
            .doctor(Doctor.builder().id(doctorId).build())
            .build();
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            patientDao.create(patient);
        }
    }

    public void updatePatient(int id, int doctorId) {
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            patientDao.updatePatientDoctor(id, doctorId);
        }
    }

    public void sort(List<Patient> patients, String sortAttributeParam) {
        SortUtil sortUtil = new SortUtil();
        SortType sortType = sortUtil.getSortType(sortAttributeParam);
        String sortAttribute = sortUtil.getSortAttribute(sortAttributeParam);

        Comparator comparator;

        switch (sortAttribute) {
            case "name": {
                comparator = Comparator.comparing(Patient::getName).thenComparing(Patient::getSurname);
                break;
            }
            case "birthday": {
                comparator = Comparator.comparing(Patient::getBirthday);
                break;
            }
            default: {
                return;
            }
        }

        comparator = sortType == SortType.DESC
            ? comparator.reversed()
            : comparator;

        patients.sort(comparator);
    }
}
