package com.nikita.controller.servlet.doctor;

import com.nikita.controller.command.doctor.DoctorCreateMedicalCardCommand;
import com.nikita.controller.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoctorMedicalCardsServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
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

        DoctorCreateMedicalCardCommand createMedicalCardCommand = new DoctorCreateMedicalCardCommand(patientId);
        createMedicalCardCommand.execute();

        resp.setStatus(200);
    }
}
