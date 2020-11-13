package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.entity.Doctor;
import com.nikita.model.service.AdminService;
import com.nikita.model.service.DoctorService;

import java.util.List;

public class GetDoctorsCommand implements Command<List<Doctor>> {
    private int[] doctorsCategories;
    private DoctorService doctorService;

    public GetDoctorsCommand(int[] doctorsCategories) {
        this.doctorsCategories = doctorsCategories;
        doctorService = new DoctorService();
    }

    public List<Doctor> execute() {
        return doctorsCategories != null && doctorsCategories.length > 0
            ? doctorService.getDoctorsWithRelations(doctorsCategories)
            : doctorService.getDoctorsWithRelations();
    }
}
