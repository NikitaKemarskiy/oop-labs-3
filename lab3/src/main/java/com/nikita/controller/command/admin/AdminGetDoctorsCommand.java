package com.nikita.controller.command.admin;

import com.nikita.controller.command.Command;
import com.nikita.model.enums.SortType;
import com.nikita.model.entity.Doctor;
import com.nikita.model.service.DoctorService;

import java.util.List;

public class AdminGetDoctorsCommand implements Command<List<Doctor>> {
    private int[] doctorsCategories;
    private String sortAttributeParam;
    private DoctorService doctorService;

    public AdminGetDoctorsCommand(int[] doctorsCategories, String sortAttributeParam) {
        this.doctorsCategories = doctorsCategories;
        this.sortAttributeParam = sortAttributeParam;
        doctorService = new DoctorService();
    }

    public List<Doctor> execute() {
        List<Doctor> doctors = doctorsCategories != null && doctorsCategories.length > 0
            ? doctorService.getDoctorsWithRelations(doctorsCategories)
            : doctorService.getDoctorsWithRelations();
        if (sortAttributeParam != null) {
            doctorService.sort(doctors, sortAttributeParam);
        }
        return doctors;
    }
}
