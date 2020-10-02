package com.hospital.model.person;

import com.hospital.model.classification.DoctorCategory;

import java.util.Date;

public class Doctor extends Person {
    private DoctorCategory category;

    public Doctor(int id, String name, String surname, DoctorCategory category, Date birthday) {
        super(id, name, surname, birthday);
        this.category = category;
    }

    public DoctorCategory getCategory() {
        return category;
    }

    public void setCategory(DoctorCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Категория: %s", category.getName());
    }

    /*** Doctor with patients number class ***/
    public static class DoctorPatientsNumber {
        Doctor doctor;
        int patientsNumber;

        public DoctorPatientsNumber(Doctor doctor, int patientsNumber) {
            this.doctor = doctor;
            this.patientsNumber = patientsNumber;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public int getPatientsNumber() {
            return patientsNumber;
        }

        public void setPatientsNumber(int patientsNumber) {
            this.patientsNumber = patientsNumber;
        }

        @Override
        public String toString() {
            return doctor.toString() + String.format("; Кол-во пациентов: %d", patientsNumber);
        }
    }
}
