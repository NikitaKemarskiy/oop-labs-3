package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.DoctorCategory;
import com.nikita.model.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PatientMapper implements EntityMapper<Patient> {
    @Override
    public Patient extractFromResultSet(ResultSet resultSet) throws SQLException {
        int doctorId = resultSet.getInt("doctorId");
        Doctor doctor = doctorId != 0
            ? Doctor.builder().id(doctorId).build()
            : null;
        return Patient.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .doctor(doctor)
            .build();
    }

    public Patient extractFromResultSetWithRelations(ResultSet resultSet) throws SQLException {
        int doctorId = resultSet.getInt("doctorId");
        Doctor doctor = doctorId != 0
            ? Doctor.builder()
                .id(doctorId)
                .name(resultSet.getString("doctorName"))
                .surname(resultSet.getString("doctorSurname"))
                .birthday(resultSet.getObject("doctorBirthday", LocalDate.class))
                .category(DoctorCategory.builder().id(resultSet.getInt("doctorCategoryId")).build())
                .build()
            : null;
        return Patient.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .doctor(doctor)
            .build();
    }
}
