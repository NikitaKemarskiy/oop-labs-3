package com.nikita.model.dao;

import java.sql.Connection;

public interface DaoFactory {
    AdminDao createAdminDao();
    DischargeDao createDischargeDao();
    DoctorCategoryDao createDoctorCategoryDao();
    DoctorDao createDoctorDao();
    MedicalCardDao createMedicalCardDao();
    MedicalCardTreatmentDao createMedicalCardTreatmentDao();
    NurseDao createNurseDao();
    PatientDao createPatientDao();
    TreatmentCategoryDao createTreatmentCategoryDao();
    TreatmentDao createTreatmentDao();
    Connection getConnection();
}
