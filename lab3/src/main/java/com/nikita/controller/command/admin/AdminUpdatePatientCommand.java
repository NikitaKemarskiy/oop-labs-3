package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.service.PatientService;

public class AdminUpdatePatientCommand implements Command {
    private int id;
    private int doctorId;
    private PatientService patientService;

    public AdminUpdatePatientCommand(int id, int doctorId) {
        this.id = id;
        this.doctorId = doctorId;
        patientService = new PatientService();
    }

    public Object execute() {
        patientService.updatePatient(id, doctorId);
        return null;
    }
}
