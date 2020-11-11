package com.nikita.model.entity;

import java.time.OffsetDateTime;

public class Discharge {
    private int id;
    private OffsetDateTime createdAt;
    private Patient patient;
    private String diagnosis;

    public Discharge(int id, OffsetDateTime createdAt, Patient patient, String diagnosis) {
        this.id = id;
        this.createdAt = createdAt;
        this.patient = patient;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public static DischargeBuilder builder() {
        return new DischargeBuilder();
    }

    public static class DischargeBuilder {
        private int id;
        private OffsetDateTime createdAt;
        private Patient patient;
        private String diagnosis;

        public DischargeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DischargeBuilder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DischargeBuilder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public DischargeBuilder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public Discharge build() {
            return new Discharge(id, createdAt, patient, diagnosis);
        }
    }
}