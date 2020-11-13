package com.nikita.model.dao.query;

public class PatientQuery {
    private PatientQuery() {}

    public static String CREATE = "insert into patients (name, surname, birthday, \"doctorId\") values (?, ?, ?, ?)";
    public static String FIND_BY_ID = "select * from patients where id = ?";
    public static String FIND_ALL = "select * from patients";
    public static String UPDATE = "update patients set name = ?, surname = ?, birthday = ?, \"doctorId\" = ? where id = ?";
    public static String DELETE = "delete from patients where id = ?";
}
