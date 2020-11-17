package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.entity.MedicalCardRecord;
import com.nikita.model.service.MedicalCardRecordService;

import java.util.List;

public class DoctorGetMedicalCardRecordsCommand implements Command<List<MedicalCardRecord>> {
    private int patientId;
    private MedicalCardRecordService medicalCardRecordService;

    public DoctorGetMedicalCardRecordsCommand(int patientId) {
        this.patientId = patientId;
        medicalCardRecordService = new MedicalCardRecordService();
    }

    public List<MedicalCardRecord> execute() {
        return medicalCardRecordService.getMedicalCardRecordsByPatientId(patientId);
    }
}