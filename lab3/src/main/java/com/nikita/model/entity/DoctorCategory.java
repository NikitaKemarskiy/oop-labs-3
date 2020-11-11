package com.nikita.model.entity;

import java.time.OffsetDateTime;

public class DoctorCategory {
    private int id;
    private String name;

    public DoctorCategory(int id, String name) {
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

    public static DoctorCategoryBuilder builder() {
        return new DoctorCategoryBuilder();
    }

    public static class DoctorCategoryBuilder {
        private int id;
        private String name;

        public DoctorCategoryBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DoctorCategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DoctorCategory build() {
            return new DoctorCategory(id, name);
        }
    }
}
