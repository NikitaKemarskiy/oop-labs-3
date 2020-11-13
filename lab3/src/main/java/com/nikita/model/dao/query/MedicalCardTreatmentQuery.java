package com.nikita.model.dao.query;

public class MedicalCardTreatmentQuery {
    private MedicalCardTreatmentQuery() {}

    public static String CREATE = "insert into \"medicalCardsTreatments\" (\"medicalCardId\", \"treatmentId\", amount) values (?, ?, ?)";
    public static String FIND_BY_ID = "select * from \"medicalCardsTreatments\" where id = ?";
    public static String FIND_ALL = "select * from \"medicalCardsTreatments\"";
    public static String UPDATE = "update \"medicalCardsTreatments\" set \"medicalCardId\" = ?, \"treatmentId\" = ?, amount = ? where id = ?";
    public static String DELETE = "delete from \"medicalCardsTreatments\" where id = ?";
}
