package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.MedicalCardRecordDao;
import com.nikita.model.dao.MedicalCardRecordTreatmentDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.dao.jdbc.JDBCMedicalCardRecordDao;
import com.nikita.model.dao.jdbc.JDBCMedicalCardRecordTreatmentDao;
import com.nikita.model.entity.MedicalCardRecordTreatment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MedicalCardRecordTreatmentServiceTest {

    MedicalCardRecordTreatmentService medicalCardRecordTreatmentService;
    DaoFactory mockDaoFactory;
    MedicalCardRecordTreatmentDao mockMedicalCardRecordTreatmentDao;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(JDBCDaoFactory.class);
        mockMedicalCardRecordTreatmentDao = mock(JDBCMedicalCardRecordTreatmentDao.class);
        medicalCardRecordTreatmentService = new MedicalCardRecordTreatmentService();

        Field daoFactoryField = medicalCardRecordTreatmentService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(medicalCardRecordTreatmentService, mockDaoFactory);
    }

    @Test
    public void updateMedicalCardRecordTreatment() {
        int medicalCardRecordTreatmentId = 1;
        int amountLeft = 1;
        MedicalCardRecordTreatment medicalCardRecordTreatment = MedicalCardRecordTreatment.builder()
            .id(medicalCardRecordTreatmentId)
            .amountLeft(amountLeft)
            .build();

        when(mockDaoFactory.createMedicalCardRecordTreatmentDao()).thenReturn(mockMedicalCardRecordTreatmentDao);

        medicalCardRecordTreatmentService.updateMedicalCardRecordTreatment(medicalCardRecordTreatmentId, amountLeft);

        verify(mockMedicalCardRecordTreatmentDao).update(medicalCardRecordTreatment);
    }
}