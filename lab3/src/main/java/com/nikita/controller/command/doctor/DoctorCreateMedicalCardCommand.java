package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.service.MedicalCardService;

public class DoctorCreateMedicalCardCommand implements Command {
    private int patientId;
    private MedicalCardService medicalCardService;

    public DoctorCreateMedicalCardCommand(int patientId) {
        this.patientId = patientId;
        medicalCardService = new MedicalCardService();
    }

    @Override
    public Object execute() {
        medicalCardService.createMedicalCard(patientId);
        return null;
    }
}
