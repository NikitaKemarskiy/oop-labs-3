package com.nikita.model.entity;

import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class DischargeTest {

    private int id = 1;
    private OffsetDateTime createdAt = OffsetDateTime.now();
    private Patient mockPatient;
    private String diagnosis = "testDiagnosis";
    private Discharge discharge;

    @Before
    public void setUp() throws Exception {
        mockPatient = mock(Patient.class);
        discharge = new Discharge(id, createdAt, mockPatient, diagnosis);
    }

    @Test
    public void getId() {
        int id = discharge.getId();

        assertEquals(this.id, id);
    }

    @Test
    public void setId() {
        int newId = 2;
        discharge.setId(newId);

        int id = discharge.getId();

        assertEquals(newId, id);
    }

    @Test
    public void getCreatedAt() {
        OffsetDateTime createdAt = discharge.getCreatedAt();

        assertEquals(this.createdAt, createdAt);
    }

    @Test
    public void setCreatedAt() {
        OffsetDateTime newCreatedAt = OffsetDateTime.now();
        discharge.setCreatedAt(newCreatedAt);

        OffsetDateTime createdAt = discharge.getCreatedAt();

        assertEquals(newCreatedAt, createdAt);
    }

    @Test
    public void getPatient() {
        Patient patient = discharge.getPatient();

        assertEquals(this.mockPatient, patient);
    }

    @Test
    public void setPatient() {
        Patient newPatient = mock(Patient.class);
        discharge.setPatient(newPatient);

        Patient patient = discharge.getPatient();

        assertEquals(newPatient, patient);
    }

    @Test
    public void getDiagnosis() {
        String diagnosis = discharge.getDiagnosis();

        assertEquals(this.diagnosis, diagnosis);
    }

    @Test
    public void setDiagnosis() {
        String newDiagnosis = "newDiagnosis";
        discharge.setDiagnosis(newDiagnosis);

        String diagnosis = discharge.getDiagnosis();

        assertEquals(newDiagnosis, diagnosis);
    }

    @Test
    public void builder() {
        Discharge discharge = Discharge.builder()
            .id(id)
            .createdAt(createdAt)
            .patient(mockPatient)
            .diagnosis(diagnosis)
            .build();
        assertEquals(discharge, this.discharge);
    }
}