package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.entity.Patient;
import com.nikita.model.service.PatientService;

import java.util.List;

public class AdminGetPatientsCommand implements Command<List<Patient>> {
    private String sortAttributeParam;
    private PatientService patientService;

    public AdminGetPatientsCommand(String sortAttributeParam) {
        this.sortAttributeParam = sortAttributeParam;
        patientService = new PatientService();
    }

    public List<Patient> execute() {
        List<Patient> patients = patientService.getPatientsWithRelations();
        if (sortAttributeParam != null) {
            patientService.sort(patients, sortAttributeParam);
        }
        return patients;
    }
}
