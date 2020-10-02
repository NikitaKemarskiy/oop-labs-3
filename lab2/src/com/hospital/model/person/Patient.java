package com.hospital.model.person;

import com.hospital.model.treatment.MedicalCard;

import java.util.Date;

public class Patient extends Person {
    private Doctor doctor;
    private MedicalCard medicalCard;

    public Patient(int id, String name, String surname, Doctor doctor, Date birthday) {
        super(id, name, surname, birthday);
        this.doctor = doctor;
        medicalCard = new MedicalCard();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void addMedicalCardRecord(String record) {
        medicalCard.addRecord(record);
    }

    @Override
    public String toString() {
        return super.toString() + (
            doctor == null
                ? ""
                : String.format("; ФИО врача: %s %s", doctor.getName(), doctor.getSurname())
        );
    }
}
