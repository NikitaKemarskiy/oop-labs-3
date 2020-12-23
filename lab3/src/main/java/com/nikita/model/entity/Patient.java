package com.nikita.model.entity;

import java.time.LocalDate;

public class Patient {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Doctor doctor;

    public Patient(int id, String name, String surname, LocalDate birthday, Doctor doctor) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public static PatientBuilder builder() {
        return new PatientBuilder();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Patient patient = (Patient) object;

        return
            id == patient.id &&
            (name == patient.name || name.equals(patient.name)) &&
            (surname == patient.surname || surname.equals(patient.surname)) &&
            (birthday == patient.birthday || birthday.equals(patient.birthday)) &&
            (doctor == patient.doctor || doctor.equals(patient.doctor));
    }

    @Override public int hashCode() {
        return id;
    }

    public static class PatientBuilder {
        private int id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private Doctor doctor;

        public PatientBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PatientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PatientBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PatientBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public PatientBuilder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Patient build() {
            return new Patient(id, name, surname, birthday, doctor);
        }
    }
}
