package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Doctor;
import com.nikita.model.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PatientMapper implements EntityMapper<Patient> {
    @Override
    public Patient extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Patient.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .doctor(Doctor.builder().id(resultSet.getInt("doctorId")).build())
            .build();
    }
}
