package com.nikita.model.entity;

public class MedicalCardTreatment {
    private MedicalCard medicalCard;
    private Treatment treatment;
    private int amount;

    public MedicalCardTreatment(MedicalCard medicalCard, Treatment treatment, int amount) {
        this.medicalCard = medicalCard;
        this.treatment = treatment;
        this.amount = amount;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
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

    public static MedicalCardTreatmentBuilder builder() {
        return new MedicalCardTreatmentBuilder();
    }

    public static class MedicalCardTreatmentBuilder {
        private MedicalCard medicalCard;
        private Treatment treatment;
        private int amount;

        public MedicalCardTreatmentBuilder medicalCard(MedicalCard medicalCard) {
            this.medicalCard = medicalCard;
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

        public MedicalCardTreatment build() {
            return new MedicalCardTreatment(medicalCard, treatment, amount);
        }
    }
}
