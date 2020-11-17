package com.nikita.model.entity;

public class MedicalCardRecordTreatment {
    private int id;
    private MedicalCardRecord medicalCardRecord;
    private Treatment treatment;
    private int amount;
    private int amountLeft;

    public MedicalCardRecordTreatment(int id, MedicalCardRecord medicalCardRecord, Treatment treatment, int amount, int amountLeft) {
        this.id = id;
        this.medicalCardRecord = medicalCardRecord;
        this.treatment = treatment;
        this.amount = amount;
        this.amountLeft = amountLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedicalCardRecord getMedicalCardRecord() {
        return medicalCardRecord;
    }

    public void setMedicalCardRecord(MedicalCardRecord medicalCardRecord) {
        this.medicalCardRecord = medicalCardRecord;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public static MedicalCardTreatmentBuilder builder() {
        return new MedicalCardTreatmentBuilder();
    }

    public static class MedicalCardTreatmentBuilder {
        private int id;
        private MedicalCardRecord medicalCardRecord;
        private Treatment treatment;
        private int amount;
        private int amountLeft;

        public MedicalCardTreatmentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MedicalCardTreatmentBuilder medicalCardRecord(MedicalCardRecord medicalCardRecord) {
            this.medicalCardRecord = medicalCardRecord;
            return this;
        }

        public MedicalCardTreatmentBuilder treatment(Treatment treatment) {
            this.treatment = treatment;
            return this;
        }

        public MedicalCardTreatmentBuilder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public MedicalCardTreatmentBuilder amountLeft(int amountLeft) {
            this.amountLeft = amountLeft;
            return this;
        }

        public MedicalCardRecordTreatment build() {
            return new MedicalCardRecordTreatment(id, medicalCardRecord, treatment, amount, amountLeft);
        }
    }
}
