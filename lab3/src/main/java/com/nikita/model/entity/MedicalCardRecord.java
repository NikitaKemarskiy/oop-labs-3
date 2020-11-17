package com.nikita.model.entity;

import java.util.Set;

public class MedicalCardRecord {
    private int id;
    private MedicalCard medicalCard;
    private String diagnosis;
    private Set<MedicalCardRecordTreatment> medicalCardRecordTreatments;

    public MedicalCardRecord(int id, MedicalCard medicalCard, String diagnosis, Set<MedicalCardRecordTreatment> medicalCardRecordTreatments) {
        this.id = id;
        this.medicalCard = medicalCard;
        this.diagnosis = diagnosis;
        this.medicalCardRecordTreatments = medicalCardRecordTreatments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Set<MedicalCardRecordTreatment> getMedicalCardRecordTreatments() {
        return medicalCardRecordTreatments;
    }

    public void setMedicalCardRecordTreatments(Set<MedicalCardRecordTreatment> medicalCardRecordTreatments) {
        this.medicalCardRecordTreatments = medicalCardRecordTreatments;
    }

    public static MedicalCardBuilder builder() {
        return new MedicalCardBuilder();
    }

    public static class MedicalCardBuilder {
        private int id;
        private MedicalCard medicalCard;
        private String diagnosis;
        private Set<MedicalCardRecordTreatment> medicalCardRecordTreatments;

        public MedicalCardBuilder medicalCard(MedicalCard medicalCard) {
            this.medicalCard = medicalCard;
            return this;
        }

        public MedicalCardBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MedicalCardBuilder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public MedicalCardBuilder medicalCardRecordTreatments(Set<MedicalCardRecordTreatment> medicalCardRecordTreatments) {
            this.medicalCardRecordTreatments = medicalCardRecordTreatments;
            return this;
        }

        public MedicalCardRecord build() {
            return new MedicalCardRecord(id, medicalCard, diagnosis, medicalCardRecordTreatments);
        }
    }
}
