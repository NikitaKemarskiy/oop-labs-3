package com.nikita.model.dao.mapper;

import com.nikita.model.entity.MedicalCardRecord;
import com.nikita.model.entity.MedicalCardRecordTreatment;
import com.nikita.model.entity.Treatment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalCardRecordTreatmentMapper implements EntityMapper<MedicalCardRecordTreatment> {
    @Override
    public MedicalCardRecordTreatment extractFromResultSet(ResultSet resultSet) throws SQLException {
        return MedicalCardRecordTreatment.builder()
            .id(resultSet.getInt("id"))
            .medicalCardRecord(MedicalCardRecord.builder().id(resultSet.getInt("medicalCardRecordId")).build())
            .treatment(Treatment.builder().id(resultSet.getInt("treatmentId")).build())
            .amount(resultSet.getInt("amount"))
            .amountLeft(resultSet.getInt("amountLeft"))
            .build();
    }
}
