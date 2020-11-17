package com.nikita.model.entity;

public class MedicalCard {
    private int id;
    private Patient patient;

    public MedicalCard(int id, Patient patient) {
        this.id = id;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public static MedicalCardBuilder builder() {
        return new MedicalCardBuilder();
    }

    public static class MedicalCardBuilder {
        private int id;
        private Patient patient;

        public MedicalCardBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MedicalCardBuilder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public MedicalCard build() {
            return new MedicalCard(id, patient);
        }
    }
}
