package com.nikita.controller.servlet.auth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("isAuth", false);
        resp.setStatus(200);
    }
}