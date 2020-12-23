package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.MedicalCardRecordDao;
import com.nikita.model.dao.MedicalCardRecordTreatmentDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.dao.jdbc.JDBCMedicalCardRecordDao;
import com.nikita.model.dao.jdbc.JDBCMedicalCardRecordTreatmentDao;
import com.nikita.model.entity.MedicalCard;
import com.nikita.model.entity.MedicalCardRecord;
import com.nikita.model.entity.MedicalCardRecordTreatment;
import com.nikita.model.entity.Treatment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MedicalCardRecordServiceTest {

    MedicalCardRecordService medicalCardRecordService;
    DaoFactory mockDaoFactory;
    MedicalCardRecordDao mockMedicalCardRecordDao;
    MedicalCardRecordTreatmentDao mockMedicalCardRecordTreatmentDao;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(JDBCDaoFactory.class);
        mockMedicalCardRecordDao = mock(JDBCMedicalCardRecordDao.class);
        mockMedicalCardRecordTreatmentDao = mock(JDBCMedicalCardRecordTreatmentDao.class);
        medicalCardRecordService = new MedicalCardRecordService();

        Field daoFactoryField = medicalCardRecordService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(medicalCardRecordService, mockDaoFactory);
    }

    @Test
    public void getMedicalCardRecordsByPatientId() {
        int id = 1;
        MedicalCard medicalCard = mock(MedicalCard.class);
        String diagnosis = "testDiagnosis";
        Set<MedicalCardRecordTreatment> medicalCardRecordTreatments = mock(Set.class);
        int patientId = 1;

        List<MedicalCardRecord> medicalCardRecords = new ArrayList<>();

        medicalCardRecords.add(new MedicalCardRecord(id, medicalCard, diagnosis, medicalCardRecordTreatments));
        medicalCardRecords.add(new MedicalCardRecord(id, medicalCard, diagnosis, medicalCardRecordTreatments));
        medicalCardRecords.add(new MedicalCardRecord(id, medicalCard, diagnosis, medicalCardRecordTreatments));

        when(mockDaoFactory.createMedicalCardRecordDao()).thenReturn(mockMedicalCardRecordDao);
        when(mockMedicalCardRecordDao.findByPatientIdWithRelations(patientId)).thenReturn(medicalCardRecords);

        assertEquals(medicalCardRecordService.getMedicalCardRecordsByPatientId(patientId), medicalCardRecords);

    }

    @Test
    public void addTreatmentToMedicalCardRecord() {
        int medicalCardRecordId = 1;
        int treatmentId = 1;
        int amount = 1;
        int amountLeft = 1;
        MedicalCardRecordTreatment medicalCardRecordTreatment = MedicalCardRecordTreatment.builder()
            .medicalCardRecord(MedicalCardRecord.builder().id(medicalCardRecordId).build())
            .treatment(Treatment.builder().id(treatmentId).build())
            .amount(amount)
            .amountLeft(amountLeft)
            .build();

        when(mockDaoFactory.createMedicalCardRecordTreatmentDao()).thenReturn(mockMedicalCardRecordTreatmentDao);

        medicalCardRecordService.addTreatmentToMedicalCardRecord(medicalCardRecordId, treatmentId, amount, amountLeft);

        verify(mockMedicalCardRecordTreatmentDao).create(medicalCardRecordTreatment);
    }

    @Test
    public void createMedicalCardRecord() {
        int medicalCardId = 1;
        String diagnosis = "testDiagnosis";
        MedicalCardRecord medicalCardRecord =MedicalCardRecord.builder()
            .medicalCard(MedicalCard.builder().id(medicalCardId).build())
            .diagnosis(diagnosis)
            .build();


        when(mockDaoFactory.createMedicalCardRecordDao()).thenReturn(mockMedicalCardRecordDao);

        medicalCardRecordService.createMedicalCardRecord(medicalCardId, diagnosis);

        verify(mockMedicalCardRecordDao).create(medicalCardRecord);
    }
}