package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.DoctorCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DoctorMapper implements EntityMapper<Doctor> {
    @Override
    public Doctor extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Doctor.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .category(DoctorCategory.builder().id(resultSet.getInt("categoryId")).build())
            .build();
    }
}
