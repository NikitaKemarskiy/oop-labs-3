package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.MedicalCardRecordDao;
import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.MedicalCardRecord;

import java.util.List;

public class MedicalCardRecordService {
    private DaoFactory daoFactory;

    public MedicalCardRecordService() {
        daoFactory = new JDBCDaoFactory();
    }

    public List<MedicalCardRecord> getMedicalCardRecordsByPatientId(int patientId) {
        try (MedicalCardRecordDao medicalCardRecordDao = daoFactory.createMedicalCardRecordDao()) {
            return medicalCardRecordDao.findByPatientIdWithRelations(patientId);
        }
    }
}
