package com.nikita.controller.servlet.auth;

import com.nikita.controller.role.LoginRole;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoctorLoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        Integer id = Integer.parseInt(req.getParameter("id"));

        if (id == null) {
            resp.setStatus(400);
            return;
        }

        httpSession.setAttribute("isAuth", true);
        httpSession.setAttribute("id", id);
        httpSession.setAttribute("role", LoginRole.DOCTOR);

        resp.setStatus(200);
    }
}