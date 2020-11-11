package com.nikita.model.entity;

import java.time.LocalDate;

public class Treatment {
    private int id;
    private String name;
    private TreatmentCategory category;

    public Treatment(int id, String name, TreatmentCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
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

    public TreatmentCategory getCategory() {
        return category;
    }

    public void setCategory(TreatmentCategory category) {
        this.category = category;
    }

    public static TreatmentBuilder builder() {
        return new TreatmentBuilder();
    }

    public static class TreatmentBuilder {
        private int id;
        private String name;
        private TreatmentCategory category;

        public TreatmentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TreatmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TreatmentBuilder category(TreatmentCategory category) {
            this.category = category;
            return this;
        }

        public Treatment build() {
            return new Treatment(id, name, category);
        }
    }
}
