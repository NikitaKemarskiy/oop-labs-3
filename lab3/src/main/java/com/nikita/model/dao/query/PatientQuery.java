package com.nikita.model.dao.query;

public class PatientQuery {
    private PatientQuery() {}

    public static String CREATE = "insert into patients (name, surname, birthday, \"doctorId\") values (?, ?, ?, ?)";
    public static String FIND_BY_ID = "select * from patients where id = ?";
    public static String FIND_ALL = "select * from patients";
    public static String UPDATE = "update patients set name = ?, surname = ?, birthday = ?, \"doctorId\" = ? where id = ?";
    public static String DELETE = "delete from patients where id = ?";

    public static String FIND_ALL_WITH_RELATIONS = "select patients.*, doctors.name as \"doctorName\", doctors.surname as \"doctorSurname\", doctors.birthday as \"doctorBirthday\", doctors.\"categoryId\" as \"doctorCategoryId\" from patients left join doctors on patients.\"doctorId\" = doctors.id";
    public static String FIND_BY_DOCTORS_IDS = "select * from patients where \"doctorId\" in (%s)";
    public static String FIND_BY_DOCTOR_ID_WITH_RELATIONS = "select patients.*, doctors.name as \"doctorName\", doctors.surname as \"doctorSurname\", doctors.birthday as \"doctorBirthday\", doctors.\"categoryId\" as \"doctorCategoryId\" from patients left join doctors on patients.\"doctorId\" = doctors.id where \"doctorId\" = ?";
    public static String UPDATE_DOCTOR = "update patients set \"doctorId\" = ? where id = ?";
}
