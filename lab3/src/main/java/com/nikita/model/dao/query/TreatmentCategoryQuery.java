package com.nikita.model.dao.query;

public class TreatmentCategoryQuery {
    private TreatmentCategoryQuery() {}

    public static String CREATE = "insert into \"treatmentsCategories\" (name) values (?)";
    public static String FIND_BY_ID = "select * from \"treatmentsCategories\" where id = ?";
    public static String FIND_ALL = "select * from \"treatmentsCategories\"";
    public static String UPDATE = "update \"treatmentsCategories\" set name = ? where id = ?";
    public static String DELETE = "delete from \"treatmentsCategories\" where id = ?";
}
