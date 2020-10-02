package com.hospital.model.classification;

public enum DoctorCategory {
    PEDIATRICIAN(0, "Педиатр"),
    TRAUMATOLOGIST(1, "Травматолог"),
    SURGEON(2, "Хирург"),
    OPHTHALMOLOGIST(3, "Офтальмолог");

    private final int value;
    private final String name;

    private DoctorCategory(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}