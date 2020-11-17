package com.nikita.controller.servlet.util;

import com.nikita.controller.enums.LoginRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtil {
    public boolean checkAuthAdmin(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        Boolean isAuth = (Boolean) httpSession.getAttribute("isAuth");
        LoginRole role = (LoginRole) httpSession.getAttribute("role");
        return isAuth != null && role != null && isAuth && role == LoginRole.ADMIN;
    }

    public boolean checkAuthDoctor(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        Boolean isAuth = (Boolean) httpSession.getAttribute("isAuth");
        Integer id = (Integer) httpSession.getAttribute("id");
        LoginRole role = (LoginRole) httpSession.getAttribute("role");
        return isAuth != null && id != null && role != null && isAuth && role == LoginRole.DOCTOR;
    }
}
