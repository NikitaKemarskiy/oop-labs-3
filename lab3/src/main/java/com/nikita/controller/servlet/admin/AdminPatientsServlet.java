package com.nikita.controller.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nikita.controller.command.admin.AdminCreatePatientCommand;
import com.nikita.controller.command.admin.AdminGetPatientsCommand;
import com.nikita.controller.command.admin.AdminUpdatePatientCommand;
import com.nikita.controller.servlet.util.ServletUtil;
import com.nikita.model.entity.Patient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class AdminPatientsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthAdmin(req)) {
            resp.setStatus(401);
            return;
        }

        String sortAttributeParam = req.getParameter("sort");

        AdminGetPatientsCommand getPatientsCommand = new AdminGetPatientsCommand(sortAttributeParam);
        List<Patient> patients = getPatientsCommand.execute();

        resp.setContentType("text/yaml; charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            objectMapper.writeValue(writer, patients);
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
        Integer doctorId = Integer.parseInt(req.getParameter("doctorId"));

        if (name == null || surname == null || birthday == null || doctorId == null) {
            resp.setStatus(400);
            return;
        }

        AdminCreatePatientCommand createPatientCommand = new AdminCreatePatientCommand(name, surname, birthday, doctorId);
        createPatientCommand.execute();

        resp.setStatus(200);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthAdmin(req)) {
            resp.setStatus(401);
            return;
        }

        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer doctorId = Integer.parseInt(req.getParameter("doctorId"));

        if (id == null || doctorId == null) {
            resp.setStatus(400);
            return;
        }

        AdminUpdatePatientCommand updatePatientCommand = new AdminUpdatePatientCommand(id, doctorId);
        updatePatientCommand.execute();

        resp.setStatus(200);
    }
}
