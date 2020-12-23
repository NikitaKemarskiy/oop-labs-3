package com.nikita.model.service;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.DoctorDao;
import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Admin;
import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.DoctorCategory;
import com.nikita.model.entity.Patient;
import com.nikita.model.enums.SortType;
import com.nikita.model.util.SortUtil;

import java.time.LocalDate;
import java.util.*;

public class DoctorService {
    private DaoFactory daoFactory;

    public DoctorService() {
        daoFactory = new JDBCDaoFactory();
    }

    public List<Doctor> getDoctorsWithRelations() {
        try (DoctorDao doctorDao = daoFactory.createDoctorDao()) {
            return doctorDao.findAllWithRelations();
        }
    }

    public List<Doctor> getDoctorsWithRelations(int[] doctorsCategories) {
        try (DoctorDao doctorDao = daoFactory.createDoctorDao()) {
            return doctorDao.findByCategoriesWithRelations(doctorsCategories);
        }
    }

    public Map<Integer, Integer> getDoctorsPatientsAmount(int[] ids) {
        try (PatientDao patientDao = daoFactory.createPatientDao()) {
            List<Patient> patients = patientDao.findByDoctorsIds(ids);
            Map<Integer, Integer> doctorsPatientsAmount = new HashMap<>();
            for (Patient patient : patients) {
                int id = patient.getDoctor().getId();
                int current = Optional.ofNullable(doctorsPatientsAmount.get(id)).orElse(0);
                doctorsPatientsAmount.put(id, current + 1);
            }
            return doctorsPatientsAmount;
        }
    }

    public void createDoctor(String name, String surname, LocalDate birthday, int categoryId) {
        Doctor doctor = Doctor.builder()
            .name(name)
            .surname(surname)
            .birthday(birthday)
            .category(DoctorCategory.builder().id(categoryId).build())
            .build();
        try (DoctorDao doctorDao = daoFactory.createDoctorDao()) {
            doctorDao.create(doctor);
        }
    }

    public void sort(List<Doctor> doctors, String sortAttributeParam) {
        SortUtil sortUtil = new SortUtil();
        SortType sortType = sortUtil.getSortType(sortAttributeParam);
        String sortAttribute = sortUtil.getSortAttribute(sortAttributeParam);

        Comparator comparator;

        switch (sortAttribute) {
            case "name": {
                comparator = Comparator.comparing(Doctor::getName).thenComparing(Doctor::getSurname);
                break;
            }
            case "category": {
                comparator = Comparator.comparing((Doctor d) -> d.getCategory().getName());
                break;
            }
            case "patientsAmount": {
                int[] ids = doctors
                    .stream()
                    .mapToInt(doctor -> doctor.getId())
                    .toArray();
                Map<Integer, Integer> doctorsPatientsAmount = getDoctorsPatientsAmount(ids);
                comparator = Comparator.comparing((Doctor d) -> doctorsPatientsAmount.get(d.getId()));
                break;
            }
            default: {
                return;
            }
        }

        comparator = sortType == SortType.DESC
            ? comparator.reversed()
            : comparator;

        doctors.sort(comparator);
    }
}
