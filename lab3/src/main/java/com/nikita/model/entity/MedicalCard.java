package com.nikita.model.entity;

import java.time.OffsetDateTime;
import java.util.Set;

public class MedicalCard {
    private int id;
    private Patient patient;
    private Set<MedicalCardTreatment> medicalCardTreatments;

    public MedicalCard(int id, Patient patient, Set<MedicalCardTreatment> medicalCardTreatments) {
        this.id = id;
        this.patient = patient;
        medicalCardTreatments = medicalCardTreatments;
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

    public Set<MedicalCardTreatment> getMedicalCardTreatments() {
        return medicalCardTreatments;
    }

    public void setMedicalCardTreatments(Set<MedicalCardTreatment> medicalCardTreatments) {
        medicalCardTreatments = medicalCardTreatments;
    }

    public static MedicalCardBuilder builder() {
        return new MedicalCardBuilder();
    }

    public static class MedicalCardBuilder {
        private int id;
        private Patient patient;
        private Set<MedicalCardTreatment> medicalCardTreatments;

        public MedicalCardBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MedicalCardBuilder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public MedicalCardBuilder medicalCardTreatments(Set<MedicalCardTreatment> medicalCardTreatments) {
            this.medicalCardTreatments = medicalCardTreatments;
            return this;
        }

        public MedicalCard build() {
            return new MedicalCard(id, patient, medicalCardTreatments);
        }
    }
}
