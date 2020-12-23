package com.nikita.model.entity;

import java.time.LocalDate;

public class Doctor {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private DoctorCategory category;

    public Doctor(int id, String name, String surname, LocalDate birthday, DoctorCategory category) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.category = category;
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

    public DoctorCategory getCategory() {
        return category;
    }

    public void setCategory(DoctorCategory category) {
        this.category = category;
    }

    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Doctor doctor = (Doctor) object;

        return
            id == doctor.id &&
            (name == doctor.name || name.equals(doctor.name)) &&
            (surname == doctor.surname || surname.equals(doctor.surname)) &&
            (birthday == doctor.birthday || birthday.equals(doctor.birthday)) &&
            (category == doctor.category || category.equals(doctor.category));
    }

    @Override public int hashCode() {
        return id;
    }

    public static class DoctorBuilder {
        private int id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private DoctorCategory category;

        public DoctorBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DoctorBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public DoctorBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public DoctorBuilder category(DoctorCategory category) {
            this.category = category;
            return this;
        }

        public Doctor build() {
            return new Doctor(id, name, surname, birthday, category);
        }
    }
}
