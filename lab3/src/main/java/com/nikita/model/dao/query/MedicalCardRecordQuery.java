package com.nikita.model.dao.query;

public class MedicalCardRecordQuery {
    private MedicalCardRecordQuery() {}

    public static String CREATE = "insert into \"medicalCardsRecords\" (\"medicalCardId\", diagnosis) values (?, ?)";
    public static String FIND_BY_ID = "select * from \"medicalCardsRecords\" where id = ?";
    public static String FIND_ALL = "select * from \"medicalCardsRecords\"";
    public static String UPDATE = "update \"medicalCardsRecords\" set diagnosis = ? where id = ?";
    public static String DELETE = "delete from \"medicalCardsRecords\" where id = ?";

    public static String FIND_BY_PATIENT_ID_WITH_RELATIONS = "select \"medicalCardsRecords\".*, \"medicalCardRecordsTreatments\".id as \"medicalCardTreatmentId\", \"medicalCardRecordsTreatments\".\"treatmentId\", \"medicalCardRecordsTreatments\".amount, \"medicalCardRecordsTreatments\".\"amountLeft\", treatments.name as \"treatmentName\", treatments.\"categoryId\" as \"treatmentCategoryId\" from \"medicalCardsRecords\" inner join \"medicalCardRecordsTreatments\" on \"medicalCardsRecords\".id = \"medicalCardRecordsTreatments\".\"medicalCardRecordId\" inner join treatments on \"medicalCardRecordsTreatments\".\"treatmentId\" = treatments.id inner join \"medicalCards\" on \"medicalCardsRecords\".\"medicalCardId\" = \"medicalCards\".id where \"patientId\" = ?";
}
