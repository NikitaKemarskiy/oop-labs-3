package com.hospital.model.person;

import com.hospital.model.classification.DoctorCategory;

import java.util.Date;

public class Nurse extends Person {
    public Nurse(int id, String name, String surname, Date birthday) {
        super(id, name, surname, birthday);
    }
}
