package com.nikita.model.dao.query;

public class NurseQuery {
    private NurseQuery() {}

    public static String CREATE = "insert into nurses (name, surname, birthday) values (?, ?, ?)";
    public static String FIND_BY_ID = "select * from nurses where id = ?";
    public static String FIND_ALL = "select * from nurses";
    public static String UPDATE = "update nurses set name = ?, surname = ?, birthday = ? where id = ?";
    public static String DELETE = "delete from nurses where id = ?";
}
