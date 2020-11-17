package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.service.MedicalCardRecordTreatmentService;

public class DoctorUpdateMedicalCardRecordTreatmentCommand implements Command {
    private int medicalCardRecordTreatmentId;
    private int amountLeft;
    private MedicalCardRecordTreatmentService medicalCardRecordTreatmentService;

    public DoctorUpdateMedicalCardRecordTreatmentCommand(
        int medicalCardRecordTreatmentId,
        int amountLeft
    ) {
        this.medicalCardRecordTreatmentId = medicalCardRecordTreatmentId;
        this.amountLeft = amountLeft;
        medicalCardRecordTreatmentService = new MedicalCardRecordTreatmentService();
    }

    public Object execute() {
        medicalCardRecordTreatmentService.updateMedicalCardRecordTreatment(
            medicalCardRecordTreatmentId,
            amountLeft
        );
        return null;
    }
}

