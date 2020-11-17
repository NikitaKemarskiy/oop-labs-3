package com.nikita.controller.servlet.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nikita.controller.command.doctor.DoctorGetMedicalCardRecordsCommand;
import com.nikita.controller.servlet.util.ServletUtil;
import com.nikita.model.entity.MedicalCardRecord;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DoctorMedicalCardsRecordsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthDoctor(req)) {
            resp.setStatus(401);
            return;
        }

        HttpSession httpSession = req.getSession();
        Integer id = (Integer) httpSession.getAttribute("id");
        Integer patientId = Integer.parseInt(req.getParameter("patientId"));

        if (id == null || patientId == null) {
            resp.setStatus(400);
            return;
        }

        DoctorGetMedicalCardRecordsCommand getMedicalCardRecordsCommand = new DoctorGetMedicalCardRecordsCommand(patientId);
        List<MedicalCardRecord> patients = getMedicalCardRecordsCommand.execute();

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
}
