package com.nikita.model.dao.query;

public class DoctorQuery {
    private DoctorQuery() {}

    public static String CREATE = "insert into doctors (name, surname, birthday, \"categoryId\") values (?, ?, ?, ?)";
    public static String FIND_BY_ID = "select * from doctors where id = ?";
    public static String FIND_ALL = "select * from doctors";
    public static String UPDATE = "update doctors set name = ?, surname = ?, birthday = ?, \"categoryId\" = ? where id = ?";
    public static String DELETE = "delete from doctors where id = ?";
}