package com.nikita.model.dao.query;

public class AdminQuery {
    private AdminQuery() {}

    public static String CREATE = "insert into admins (username, password) values (?, ?)";
    public static String FIND_BY_ID = "select * from admins where id = ?";
    public static String FIND_ALL = "select * from admins";
    public static String UPDATE = "update admins set username = ?, password = ? where id = ?";
    public static String DELETE = "delete from admins where id = ?";
}
