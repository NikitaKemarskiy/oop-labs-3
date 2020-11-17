package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.entity.Discharge;
import com.nikita.model.entity.Patient;
import com.nikita.model.service.DischargeService;

import java.util.List;

public class DoctorCreateDischargeCommand implements Command {
    private int patientId;
    private String diagnosis;
    private DischargeService dischargeService;

    public DoctorCreateDischargeCommand(
        int patientId,
        String diagnosis
    ) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        dischargeService = new DischargeService();
    }

    public Object execute() {
        dischargeService.createDischarge(patientId, diagnosis);
        return null;
    }
}
