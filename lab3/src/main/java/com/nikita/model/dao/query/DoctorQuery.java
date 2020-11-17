package com.nikita.model.dao.query;

public class DoctorQuery {
    private DoctorQuery() {}

    public static String CREATE = "insert into doctors (name, surname, birthday, \"categoryId\") values (?, ?, ?, ?)";
    public static String FIND_BY_ID = "select * from doctors where id = ?";
    public static String FIND_ALL = "select * from doctors";
    public static String UPDATE = "update doctors set name = ?, surname = ?, birthday = ?, \"categoryId\" = ? where id = ?";
    public static String DELETE = "delete from doctors where id = ?";

    public static String FIND_ALL_WITH_RELATIONS = "select doctors.*, \"doctorsCategories\".name as \"categoryName\" from doctors inner join \"doctorsCategories\" on doctors.\"categoryId\" = \"doctorsCategories\".id";
    public static String FIND_BY_CATEGORIES_WITH_RELATIONS = "select patients.*, doctors.name as \"doctorName\", doctors.surname as \"doctorSurname\", doctors.birthday as \"doctorBirthday\", doctors.\"categoryId\" as \"doctorCategoryId\" from patients inner join doctors on patients.\"doctorId\" = doctors.id";
}