package com.nikita.model.service;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.DoctorDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Admin;
import com.nikita.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public class DoctorService {
    private DaoFactory daoFactory;

    public DoctorService() {
        daoFactory = new JDBCDaoFactory();
    }

    public List<Doctor> getDoctorsWithRelations() {
        try (DoctorDao doctorDao = daoFactory.createDoctorDao()) {
            return doctorDao.findAllWithRelations();
        }
    }

    public List<Doctor> getDoctorsWithRelations(int[] doctorsCategories) {
        try (DoctorDao doctorDao = daoFactory.createDoctorDao()) {
            return doctorDao.findByCategoriesWithRelations(doctorsCategories);
        }
    }
}
