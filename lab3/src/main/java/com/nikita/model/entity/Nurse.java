package com.nikita.model.entity;

import java.time.LocalDate;

public class Nurse {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;

    public Nurse(int id, String name, String surname, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
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

    public static NurseBuilder builder() {
        return new NurseBuilder();
    }

    public static class NurseBuilder {
        private int id;
        private String name;
        private String surname;
        private LocalDate birthday;

        public NurseBuilder id(int id) {
            this.id = id;
            return this;
        }

        public NurseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public NurseBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public NurseBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Nurse build() {
            return new Nurse(id, name, surname, birthday);
        }
    }
}
