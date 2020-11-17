package com.nikita.controller.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nikita.controller.command.admin.AdminCreateDoctorCommand;
import com.nikita.controller.command.admin.AdminGetDoctorsCommand;
import com.nikita.controller.servlet.util.ServletUtil;
import com.nikita.model.entity.Doctor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdminDoctorsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthAdmin(req)) {
            resp.setStatus(401);
            return;
        }

        String[] doctorsCategoriesParams = req.getParameterValues("doctorCategory");
        int[] doctorsCategories = doctorsCategoriesParams != null
            ?
                Arrays.stream(req.getParameterValues("doctorCategory"))
                .mapToInt(doctorCategory -> Integer.parseInt(doctorCategory))
                .toArray()
            : null;
        String sortAttributeParam = req.getParameter("sort");

        AdminGetDoctorsCommand getDoctorsCommand = new AdminGetDoctorsCommand(doctorsCategories, sortAttributeParam);
        List<Doctor> doctors = getDoctorsCommand.execute();

        resp.setContentType("text/yaml; charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            objectMapper.writeValue(writer, doctors);
            resp.setStatus(200);
        } catch (IOException err) {
            System.err.println(err);
            resp.setStatus(500);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthAdmin(req)) {
            resp.setStatus(401);
            return;
        }

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));

        if (name == null || surname == null || birthday == null || categoryId == null) {
            resp.setStatus(400);
            return;
        }

        AdminCreateDoctorCommand createDoctorCommand = new AdminCreateDoctorCommand(name, surname, birthday, categoryId);
        createDoctorCommand.execute();

        resp.setStatus(200);
    }
}