package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.DischargeDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.dao.jdbc.JDBCDischargeDao;
import com.nikita.model.entity.Discharge;
import com.nikita.model.entity.Patient;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class DischargeServiceTest {

    DischargeService dischargeService;
    DaoFactory mockDaoFactory;
    DischargeDao mockDischargeDao;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(JDBCDaoFactory.class);
        mockDischargeDao = mock(JDBCDischargeDao.class);
        dischargeService = new DischargeService();

        Field daoFactoryField = dischargeService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(dischargeService, mockDaoFactory);
    }

    @Test
    public void getDischarges() {
        int id = 1;
        OffsetDateTime createdAt = OffsetDateTime.now();
        Patient mockPatient = mock(Patient.class);
        String diagnosis = "testDiagnosis";

        List<Discharge> discharges = new ArrayList<>();

        discharges.add(new Discharge(id, createdAt, mockPatient, diagnosis));
        discharges.add(new Discharge(id, createdAt, mockPatient, diagnosis));
        discharges.add(new Discharge(id, createdAt, mockPatient, diagnosis));

        when(mockDaoFactory.createDischargeDao()).thenReturn(mockDischargeDao);
        when(mockDischargeDao.findAll()).thenReturn(discharges);

        assertEquals(dischargeService.getDischarges(), discharges);
    }

    @Test
    public void createDischarge() {
        int patientId = 1;
        String diagnosis = "testDiagnosis";

        when(mockDaoFactory.createDischargeDao()).thenReturn(mockDischargeDao);

        Discharge discharge = Discharge.builder()
            .patient(Patient.builder().id(patientId).build())
            .diagnosis(diagnosis)
            .build();

        dischargeService.createDischarge(patientId, diagnosis);

        verify(mockDischargeDao).create(discharge);
    }
}