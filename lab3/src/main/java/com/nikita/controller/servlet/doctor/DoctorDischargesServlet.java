package com.nikita.controller.servlet.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nikita.controller.command.doctor.DoctorCreateDischargeCommand;
import com.nikita.controller.command.doctor.DoctorGetDischargesCommand;
import com.nikita.controller.servlet.util.ServletUtil;
import com.nikita.model.entity.Discharge;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DoctorDischargesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthDoctor(req)) {
            resp.setStatus(401);
            return;
        }

        DoctorGetDischargesCommand getDischargesCommand = new DoctorGetDischargesCommand();
        List<Discharge> patients = getDischargesCommand.execute();

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
        if (!new ServletUtil().checkAuthDoctor(req)) {
            resp.setStatus(401);
            return;
        }

        Integer patientId = Integer.parseInt(req.getParameter("patientId"));
        String diagnosis = req.getParameter("diagnosis");

        if (patientId == null || diagnosis == null) {
            resp.setStatus(400);
            return;
        }

        DoctorCreateDischargeCommand createDischargeCommand = new DoctorCreateDischargeCommand(
            patientId,
            diagnosis
        );
        createDischargeCommand.execute();

        resp.setStatus(200);
    }
}
