package com.hospital.model.person;

import com.hospital.model.dao.IdObject;

import java.util.Date;

public abstract class Person extends IdObject {
    private String name;
    private String surname;
    private Date birthday;

    Person(int id, String name, String surname, Date birthday) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; ФИО: %s %s; Дата рождения: %tF", name, surname, birthday);
    }
}
