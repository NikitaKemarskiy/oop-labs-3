package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.service.MedicalCardRecordService;

public class DoctorCreateMedicalCardRecordCommand implements Command {
    private int medicalCardId;
    private String diagnosis;
    private MedicalCardRecordService medicalCardRecordService;

    public DoctorCreateMedicalCardRecordCommand(int medicalCardId, String diagnosis) {
        this.medicalCardId = medicalCardId;
        this.diagnosis = diagnosis;
        medicalCardRecordService = new MedicalCardRecordService();
    }

    @Override
    public Object execute() {
        medicalCardRecordService.createMedicalCardRecord(medicalCardId, diagnosis);
        return null;
    }
}
