package com.nikita.controller.servlet.auth;

import com.nikita.controller.command.auth.AdminLoginCommand;
import com.nikita.controller.enums.LoginRole;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null) {
            resp.setStatus(400);
            return;
        }

        AdminLoginCommand loginCommand = new AdminLoginCommand(username, password);
        boolean isAuth = loginCommand.execute();

        if (!isAuth) {
            resp.setStatus(401);
            return;
        }

        httpSession.setAttribute("isAuth", true);
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("role", LoginRole.ADMIN);

        resp.setStatus(200);
    }
}