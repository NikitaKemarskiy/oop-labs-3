package com.nikita.model.dao.jdbc;

import com.nikita.model.connection.ConnectionPoolHolder;
import com.nikita.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory implements DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public AdminDao createAdminDao() {
        return new JDBCAdminDao(getConnection());
    }
    @Override
    public DischargeDao createDischargeDao() {
        return new JDBCDischargeDao(getConnection());
    }
    @Override
    public DoctorCategoryDao createDoctorCategoryDao() {
        return new JDBCDoctorCategoryDao(getConnection());
    }
    @Override
    public DoctorDao createDoctorDao() {
        return new JDBCDoctorDao(getConnection());
    }
    @Override
    public MedicalCardDao createMedicalCardDao() {
        return new JDBCMedicalCardDao(getConnection());
    }
    @Override
    public MedicalCardTreatmentDao createMedicalCardTreatmentDao() {
        return new JDBCMedicalCardTreatmentDao(getConnection());
    }
    @Override
    public NurseDao createNurseDao() {
        return new JDBCNurseDao(getConnection());
    }
    @Override
    public PatientDao createPatientDao() {
        return new JDBCPatientDao(getConnection());
    }
    @Override
    public TreatmentCategoryDao createTreatmentCategoryDao() {
        return new JDBCTreatmentCategoryDao(getConnection());
    }
    @Override
    public TreatmentDao createTreatmentDao() {
        return new JDBCTreatmentDao(getConnection());
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException err) {
            System.err.println("XXX Error with getting connection");
            throw new RuntimeException(err);
        }
    }
}
