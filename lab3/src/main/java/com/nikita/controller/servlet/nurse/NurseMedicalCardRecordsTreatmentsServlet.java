package com.nikita.controller.servlet.nurse;

import com.nikita.controller.command.doctor.DoctorUpdateMedicalCardRecordTreatmentCommand;
import com.nikita.controller.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NurseMedicalCardRecordsTreatmentsServlet extends HttpServlet {
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) {
    if (!new ServletUtil().checkAuthNurse(req)) {
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
