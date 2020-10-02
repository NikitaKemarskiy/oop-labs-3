package com.hospital.model.dao;

import com.hospital.model.classification.DoctorCategory;
import com.hospital.model.person.Doctor;
import com.hospital.model.person.Patient;
import com.hospital.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class DoctorDao extends Dao<Doctor> {
    private Map<Integer, Doctor> doctors = new HashMap<>();

    public DoctorDao() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(currentId++, "Paul", "Trocuk", DoctorCategory.OPHTHALMOLOGIST, Util.getRandomDate()));
        doctors.add(new Doctor(currentId++, "Alex", "Filatov", DoctorCategory.PEDIATRICIAN, Util.getRandomDate()));
        doctors.add(new Doctor(currentId++, "Yarik", "Obruch", DoctorCategory.SURGEON, Util.getRandomDate()));
        for (Doctor doctor : doctors) {
            this.doctors.put(doctor.getId(), doctor);
        }
    }

    @Override
    public Doctor get(int id) {
        return doctors.get(id);
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(doctors.values());
    }

    @Override
    public void save(Doctor doctor) {
        doctors.put(doctor.getId(), doctor);
    }

    @Override
    public void update(Doctor doctor) {
        doctors.replace(doctor.getId(), doctor);
    }

    @Override
    public void delete(int id) {
        doctors.remove(id);
    }

    public List<Doctor> getByCategory(DoctorCategory category) {
        return doctors.values()
            .stream()
            .filter(doctor -> doctor.getCategory().equals(category))
            .collect(Collectors.toList());
    }

    public List<Doctor.DoctorPatientsNumber> getDoctorPatientsNumbers(List<Doctor> doctors, PatientDao patientDao) {
        List<Patient> patients = patientDao.getAll();
        Map<Doctor, Integer> doctorPatientsNumberMap = Util.listToMapWithDefaultValue(doctors, 0);
        for (Patient patient : patients) {
            Doctor doctor = patient.getDoctor();
            boolean isDoctorPresent = doctorPatientsNumberMap.containsKey(doctor);
            if (!isDoctorPresent) {
                continue;
            }
            doctorPatientsNumberMap.put(doctor, doctorPatientsNumberMap.get(doctor) + 1);
        }
        List<Doctor.DoctorPatientsNumber> doctorPatientsNumbers = new ArrayList<>();
        for (Map.Entry<Doctor, Integer> entry : doctorPatientsNumberMap.entrySet()) {
            doctorPatientsNumbers.add(new Doctor.DoctorPatientsNumber(entry.getKey(), entry.getValue()));
        }
        return doctorPatientsNumbers;
    }
}