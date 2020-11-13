package com.nikita.model.dao.mapper;

import com.nikita.model.entity.MedicalCard;
import com.nikita.model.entity.MedicalCardTreatment;
import com.nikita.model.entity.Treatment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalCardTreatmentMapper implements EntityMapper<MedicalCardTreatment> {
    @Override
    public MedicalCardTreatment extractFromResultSet(ResultSet resultSet) throws SQLException {
        return MedicalCardTreatment.builder()
            .id(resultSet.getInt("id"))
            .medicalCard(MedicalCard.builder().id(resultSet.getInt("medicalCardId")).build())
            .treatment(Treatment.builder().id(resultSet.getInt("treatmentId")).build())
            .amount(resultSet.getInt("amount"))
            .build();
    }
}
