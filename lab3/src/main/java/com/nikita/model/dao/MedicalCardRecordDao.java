package com.nikita.model.dao;

import com.nikita.model.entity.MedicalCardRecord;

import java.util.List;

public interface MedicalCardRecordDao extends GenericDao<MedicalCardRecord> {
    List<MedicalCardRecord> findByPatientIdWithRelations(int patientId);
}
