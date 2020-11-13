package com.nikita.model.dao.mapper;

import com.nikita.model.entity.MedicalCard;
import com.nikita.model.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalCardMapper implements EntityMapper<MedicalCard> {
    @Override
    public MedicalCard extractFromResultSet(ResultSet resultSet) throws SQLException {
        return MedicalCard.builder()
            .id(resultSet.getInt("id"))
            .patient(Patient.builder().id(resultSet.getInt("patientId")).build())
            .build();
    }
}
