package com.nikita.model.dao.mapper;

import com.nikita.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class MedicalCardRecordMapper implements EntityMapper<MedicalCardRecord> {
    @Override
    public MedicalCardRecord extractFromResultSet(ResultSet resultSet) throws SQLException {
        return MedicalCardRecord.builder()
            .id(resultSet.getInt("id"))
            .medicalCard(MedicalCard.builder().id(resultSet.getInt("medicalCardId")).build())
            .diagnosis(resultSet.getString("diagnosis"))
            .build();
    }

    public MedicalCardRecord extractFromResultSetWithRelations(
        ResultSet resultSet,
        Map<Integer, MedicalCardRecord> medicalCardRecordMap
    ) throws SQLException {
        MedicalCardRecord medicalCardRecord = makeUnique(medicalCardRecordMap, extractFromResultSet(resultSet));
        MedicalCardRecordTreatment medicalCardRecordTreatment = MedicalCardRecordTreatment.builder()
            .id(resultSet.getInt("medicalCardTreatmentId"))
            .treatment(
                Treatment.builder()
                    .id(resultSet.getInt("treatmentId"))
                    .name(resultSet.getString("treatmentName"))
                    .category(TreatmentCategory.builder().id(resultSet.getInt("treatmentCategoryId")).build())
                    .build()
            )
            .amount(resultSet.getInt("amount"))
            .amountLeft(resultSet.getInt("amountLeft"))
            .build();
        medicalCardRecord.getMedicalCardRecordTreatments().add(medicalCardRecordTreatment);
        return medicalCardRecord;
    }

    public MedicalCardRecord makeUnique(Map<Integer, MedicalCardRecord> medicalCardRecordMap, MedicalCardRecord entity) {
        entity.setMedicalCardRecordTreatments(new HashSet<>());
        medicalCardRecordMap.putIfAbsent(entity.getId(), entity);
        return medicalCardRecordMap.get(entity.getId());
    }
}