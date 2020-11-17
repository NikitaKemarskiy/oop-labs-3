package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.service.PatientService;

import java.time.LocalDate;

public class AdminCreatePatientCommand implements Command {
    private String name;
    private String surname;
    private LocalDate birthday;
    private int doctorId;
    private PatientService patientService;

    public AdminCreatePatientCommand(String name, String surname, LocalDate birthday, int doctorId) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.doctorId = doctorId;
        patientService = new PatientService();
    }

    public Object execute() {
        patientService.createPatient(name, surname, birthday, doctorId);
        return null;
    }
}