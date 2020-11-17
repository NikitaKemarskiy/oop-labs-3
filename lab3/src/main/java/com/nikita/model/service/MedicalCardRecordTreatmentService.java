package com.nikita.model.service;

import com.nikita.model.dao.*;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.*;

public class MedicalCardRecordTreatmentService {
    private DaoFactory daoFactory;

    public MedicalCardRecordTreatmentService() {
        daoFactory = new JDBCDaoFactory();
    }

   public void updateMedicalCardRecordTreatment(int medicalCardRecordTreatmentId, int amountLeft) {
        try (MedicalCardRecordTreatmentDao medicalCardRecordTreatmentDao = daoFactory.createMedicalCardRecordTreatmentDao()) {
            medicalCardRecordTreatmentDao.update(
                MedicalCardRecordTreatment.builder()
                    .id(medicalCardRecordTreatmentId)
                    .amountLeft(amountLeft)
                    .build()
            );
        }
   }
}
