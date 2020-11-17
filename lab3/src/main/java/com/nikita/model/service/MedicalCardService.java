package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.MedicalCardDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.MedicalCard;
import com.nikita.model.entity.Patient;

public class MedicalCardService {
    private DaoFactory daoFactory;

    public MedicalCardService() {
        daoFactory = new JDBCDaoFactory();
    }

    public void createMedicalCard(int patientId) {
        try (MedicalCardDao medicalCardDao = daoFactory.createMedicalCardDao()) {
            medicalCardDao.create(
                MedicalCard.builder()
                    .patient(Patient.builder().id(patientId).build())
                    .build()
            );
        }
    }
}
