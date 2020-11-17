package com.nikita.model.dao.query;

public class MedicalCardRecordTreatmentQuery {
    private MedicalCardRecordTreatmentQuery() {}

    public static String CREATE = "insert into \"medicalCardRecordsTreatments\" (\"medicalCardRecordId\", \"treatmentId\", amount, \"amountLeft\") values (?, ?, ?, ?)";
    public static String FIND_BY_ID = "select * from \"medicalCardsTreatments\" where id = ?";
    public static String FIND_ALL = "select * from \"medicalCardsTreatments\"";
    public static String UPDATE = "update \"medicalCardRecordsTreatments\" set \"amountLeft\" = ? where id = ?";
    public static String DELETE = "delete from \"medicalCardsTreatments\" where id = ?";
}