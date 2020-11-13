package com.nikita.model.dao.query;

public class DoctorCategoryQuery {
    private DoctorCategoryQuery() {}

    public static String CREATE = "insert into \"doctorsCategories\" (name) values (?)";
    public static String FIND_BY_ID = "select * from \"doctorsCategories\" where id = ?";
    public static String FIND_ALL = "select * from \"doctorsCategories\"";
    public static String UPDATE = "update \"doctorsCategories\" set name = ? where id = ?";
    public static String DELETE = "delete from \"doctorsCategories\" where id = ?";
}
