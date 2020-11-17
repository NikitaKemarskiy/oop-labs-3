package com.nikita.controller.command.doctor;

import com.nikita.controller.command.Command;
import com.nikita.model.entity.Patient;
import com.nikita.model.service.PatientService;

import java.util.List;

public class DoctorGetPatientsCommand implements Command<List<Patient>> {
    private int id;
    private PatientService patientService;

    public DoctorGetPatientsCommand(int id) {
        this.id = id;
        patientService = new PatientService();
    }

    public List<Patient> execute() {
        return patientService.getPatientsByDoctorIdWIthRelations(id);
    }
}
