package com.nikita.model.service;

import com.nikita.model.dao.*;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.*;

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

    public void addTreatmentToMedicalCardRecord(
        int medicalCardRecordId,
        int treatmentId,
        int amount,
        int amountLeft
    ) {
        try (MedicalCardRecordTreatmentDao medicalCardRecordTreatmentDao = daoFactory.createMedicalCardRecordTreatmentDao()) {
            medicalCardRecordTreatmentDao.create(
                MedicalCardRecordTreatment.builder()
                    .medicalCardRecord(MedicalCardRecord.builder().id(medicalCardRecordId).build())
                    .treatment(Treatment.builder().id(treatmentId).build())
                    .amount(amount)
                    .amountLeft(amountLeft)
                    .build()
            );
        }
    }

    public void createMedicalCardRecord(int medicalCardId, String diagnosis) {
        try (MedicalCardRecordDao medicalCardRecordDao = daoFactory.createMedicalCardRecordDao()) {
            medicalCardRecordDao.create(
                MedicalCardRecord.builder()
                    .medicalCard(MedicalCard.builder().id(medicalCardId).build())
                    .diagnosis(diagnosis)
                    .build()
            );
        }
    }
}
