package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Discharge;
import com.nikita.model.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class DischargeMapper implements EntityMapper<Discharge> {
    @Override
    public Discharge extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Discharge.builder()
            .id(resultSet.getInt("id"))
            .patient(Patient.builder().id(resultSet.getInt("patientId")).build())
            .createdAt(resultSet.getObject("createdAt", OffsetDateTime.class))
            .diagnosis(resultSet.getString("diagnosis"))
            .build();
    }
}