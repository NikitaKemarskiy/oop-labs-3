package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.service.DoctorService;

import java.time.LocalDate;

public class AdminCreateDoctorCommand implements Command {
    private String name;
    private String surname;
    private LocalDate birthday;
    private int categoryId;
    private DoctorService doctorService;

    public AdminCreateDoctorCommand(String name, String surname, LocalDate birthday, int categoryId) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.categoryId = categoryId;
        doctorService = new DoctorService();
    }

    public Object execute() {
        doctorService.createDoctor(name, surname, birthday, categoryId);
        return null;
    }
}