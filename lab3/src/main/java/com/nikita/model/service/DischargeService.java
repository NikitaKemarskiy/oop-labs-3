package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.DischargeDao;
import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Discharge;
import com.nikita.model.entity.Patient;

import java.util.List;

public class DischargeService {
    private DaoFactory daoFactory;

    public DischargeService() {
        daoFactory = new JDBCDaoFactory();
    }

    public List<Discharge> getDischarges() {
        try (DischargeDao dischargeDao = daoFactory.createDischargeDao()) {
            return dischargeDao.findAll();
        }
    }

    public void createDischarge(int patientId, String diagnosis) {
        try (DischargeDao dischargeDao = daoFactory.createDischargeDao()) {
            dischargeDao.create(
                Discharge.builder()
                    .patient(Patient.builder().id(patientId).build())
                    .diagnosis(diagnosis)
                    .build()
            );
        }
    }
}
