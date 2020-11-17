package com.nikita.controller.command.doctor;

import com.nikita.model.entity.Discharge;
import com.nikita.model.entity.Patient;
import com.nikita.model.service.DischargeService;

import java.util.List;

public class DoctorGetDischargesCommand {
    private DischargeService dischargeService;

    public DoctorGetDischargesCommand() {
        dischargeService = new DischargeService();
    }

    public List<Discharge> execute() {
        return dischargeService.getDischarges();
    }
}
