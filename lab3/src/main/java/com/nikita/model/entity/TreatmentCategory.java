package com.nikita.model.entity;

public class TreatmentCategory {
    private int id;
    private String name;

    public TreatmentCategory(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static TreatmentCategoryBuilder builder() {
        return new TreatmentCategoryBuilder();
    }

    public static class TreatmentCategoryBuilder {
        private int id;
        private String name;

        public TreatmentCategoryBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TreatmentCategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TreatmentCategory build() {
            return new TreatmentCategory(id, name);
        }
    }
}
