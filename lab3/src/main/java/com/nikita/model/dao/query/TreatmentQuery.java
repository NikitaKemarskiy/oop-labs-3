package com.nikita.model.dao.query;

public class TreatmentQuery {
    private TreatmentQuery() {}

    public static String CREATE = "insert into treatments (name, \"categoryId\") values (?, ?)";
    public static String FIND_BY_ID = "select * from treatments where id = ?";
    public static String FIND_ALL = "select * from treatments";
    public static String UPDATE = "update treatments set name = ?, \"categoryId\" = ? where id = ?";
    public static String DELETE = "delete from treatments where id = ?";
}
