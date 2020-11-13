package com.nikita.model.dao.query;

public class MedicalCardQuery {
    private MedicalCardQuery() {}

    public static String CREATE = "insert into \"medicalCards\" (\"patientId\") values (?)";
    public static String FIND_BY_ID = "select * from \"medicalCards\" where id = ?";
    public static String FIND_ALL = "select * from \"medicalCards\"";
    public static String UPDATE = "update \"medicalCards\" set \"patientId\" = ? where id = ?";
    public static String DELETE = "delete from \"medicalCards\" where id = ?";
}
