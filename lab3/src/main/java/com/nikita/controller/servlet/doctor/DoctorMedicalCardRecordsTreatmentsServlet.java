package com.nikita.controller.servlet.doctor;

import com.nikita.controller.command.doctor.DoctorCreateMedicalCardRecordTreatmentCommand;
import com.nikita.controller.command.doctor.DoctorUpdateMedicalCardRecordTreatmentCommand;
import com.nikita.controller.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoctorMedicalCardRecordsTreatmentsServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthDoctor(req)) {
            resp.setStatus(401);
            return;
        }

        Integer medicalCardRecordId = Integer.parseInt(req.getParameter("medicalCardRecordId"));
        Integer treatmentId = Integer.parseInt(req.getParameter("treatmentId"));
        Integer amount = Integer.parseInt(req.getParameter("amount"));

        if (medicalCardRecordId == null || treatmentId == null || amount == null) {
            resp.setStatus(400);
            return;
        }

        DoctorCreateMedicalCardRecordTreatmentCommand createMedicalCardRecordTreatmentCommand = new DoctorCreateMedicalCardRecordTreatmentCommand(
            medicalCardRecordId,
            treatmentId,
            amount
        );
        createMedicalCardRecordTreatmentCommand.execute();

        resp.setStatus(200);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) {
        if (!new ServletUtil().checkAuthDoctor(req)) {
            resp.setStatus(401);
            return;
        }

        Integer medicalCardRecordTreatmentId = Integer.parseInt(req.getParameter("medicalCardRecordTreatmentId"));
        Integer amountLeft = Integer.parseInt(req.getParameter("amountLeft"));

        if (medicalCardRecordTreatmentId == null || amountLeft == null) {
            resp.setStatus(400);
            return;
        }

        DoctorUpdateMedicalCardRecordTreatmentCommand updateMedicalCardRecordTreatmentCommand = new DoctorUpdateMedicalCardRecordTreatmentCommand(
            medicalCardRecordTreatmentId,
            amountLeft
        );
        updateMedicalCardRecordTreatmentCommand.execute();

        resp.setStatus(200);
    }
}
