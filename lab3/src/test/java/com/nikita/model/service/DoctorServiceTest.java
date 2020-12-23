package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.DoctorDao;
import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.dao.jdbc.JDBCDoctorDao;
import com.nikita.model.dao.jdbc.JDBCPatientDao;
import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.DoctorCategory;
import com.nikita.model.entity.Patient;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DoctorServiceTest {

    DoctorService doctorService;
    DaoFactory mockDaoFactory;
    DoctorDao mockDoctorDao;
    PatientDao mockPatientDao;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(JDBCDaoFactory.class);
        mockDoctorDao = mock(JDBCDoctorDao.class);
        mockPatientDao = mock(JDBCPatientDao.class);
        doctorService = new DoctorService();

        Field daoFactoryField = doctorService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(doctorService, mockDaoFactory);
    }

    @Test
    public void getDoctorsWithRelations() {
        int id = 1;
        String name = "testName";
        String surname = "testSurname";
        LocalDate localDate = LocalDate.now();
        DoctorCategory doctorCategory = new DoctorCategory(id, "testCategoryName");

        List<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));
        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));
        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));

        when(mockDaoFactory.createDoctorDao()).thenReturn(mockDoctorDao);
        when(mockDoctorDao.findAllWithRelations()).thenReturn(doctors);

        assertEquals(doctorService.getDoctorsWithRelations(), doctors);
    }

    @Test
    public void getDoctorsWithRelationsByDoctorsCategoriesIds() {
        int id = 1;
        String name = "testName";
        String surname = "testSurname";
        LocalDate localDate = LocalDate.now();
        DoctorCategory doctorCategory = new DoctorCategory(id, "testCategoryName");
        int[] doctorsCategoriesIds = {1, 2, 3};

        List<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));
        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));
        doctors.add(new Doctor(id, name, surname, localDate, doctorCategory));

        when(mockDaoFactory.createDoctorDao()).thenReturn(mockDoctorDao);
        when(mockDoctorDao.findByCategoriesWithRelations(doctorsCategoriesIds)).thenReturn(doctors);

        assertEquals(doctorService.getDoctorsWithRelations(doctorsCategoriesIds), doctors);
    }

    @Test
    public void getDoctorsPatientsAmount() {
        int id = 1;
        String name = "testName";
        String surname = "testSurname";
        LocalDate birthday = LocalDate.now();
        Doctor mockDoctor = mock(Doctor.class);

        int[] ids = {1, 2, 3};

        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient(id, name, surname, birthday, mockDoctor));
        patients.add(new Patient(id, name, surname, birthday, mockDoctor));
        patients.add(new Patient(id, name, surname, birthday, mockDoctor));

        when(mockDaoFactory.createPatientDao()).thenReturn(mockPatientDao);
        when(mockPatientDao.findByDoctorsIds(ids)).thenReturn(patients);
        when(mockDoctor.getId()).thenReturn(1, 1, 2);

        Map<Integer, Integer> doctorsPatientsAmount = new HashMap<>();

        doctorsPatientsAmount.put(1, 2);
        doctorsPatientsAmount.put(2, 1);

        assertEquals(doctorService.getDoctorsPatientsAmount(ids), doctorsPatientsAmount);
    }

    @Test
    public void createDoctor() {
        int id = 0;
        String name = "testName";
        String surname = "testSurname";
        LocalDate birthday = LocalDate.now();
        int categoryId = 1;
        DoctorCategory doctorCategory = DoctorCategory.builder()
            .id(categoryId)
            .build();
        Doctor doctor = new Doctor(id, name, surname, birthday, doctorCategory);

        when(mockDaoFactory.createDoctorDao()).thenReturn(mockDoctorDao);

        doctorService.createDoctor(
            name,
            surname,
            birthday,
            categoryId
        );

        verify(mockDoctorDao).create(doctor);
    }

    @Test
    public void sort() {
        int id = 1;
        String surname = "testSurname";
        LocalDate localDate = LocalDate.now();
        DoctorCategory doctorCategory = new DoctorCategory(id, "testCategoryName");

        Doctor doctor1 = new Doctor(id, "testName1", surname, localDate, doctorCategory);
        Doctor doctor2 = new Doctor(id, "testName2", "testSurname1", localDate, doctorCategory);
        Doctor doctor3 = new Doctor(id, "testName2", "testSurname2", localDate, doctorCategory);

        List<Doctor> doctors = new ArrayList<>();
        List<Doctor> doctorsSorted = new ArrayList<>();

        doctors.add(doctor2);
        doctors.add(doctor1);
        doctors.add(doctor3);

        doctorsSorted.add(doctor1);
        doctorsSorted.add(doctor2);
        doctorsSorted.add(doctor3);

        doctorService.sort(doctors, "name");

        assertEquals(doctors, doctorsSorted);
    }
}