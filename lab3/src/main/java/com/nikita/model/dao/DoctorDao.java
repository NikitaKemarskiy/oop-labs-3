package com.nikita.model.dao;

import com.nikita.model.entity.Doctor;

import java.util.List;

public interface DoctorDao extends GenericDao<Doctor> {
    List<Doctor> findAllWithRelations();
    List<Doctor> findByCategoriesWithRelations(int[] categories);
}
