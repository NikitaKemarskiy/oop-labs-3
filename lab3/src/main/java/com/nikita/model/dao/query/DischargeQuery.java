package com.nikita.model.dao.query;

public class DischargeQuery {
    private DischargeQuery() {}

    public static String CREATE = "insert into discharges (\"patientId\", diagnosis) values (?, ?)";
    public static String FIND_BY_ID = "select * from discharges where id = ?";
    public static String FIND_ALL = "select * from discharges";
    public static String UPDATE = "update discharges set \"patientId\" = ?, diagnosis = ? where id = ?";
    public static String DELETE = "delete from discharges where id = ?";
}
