package com.nikita.controller.command.auth;


import com.nikita.controller.command.Command;
import com.nikita.model.service.AdminService;

public class AdminLoginCommand implements Command<Boolean> {
    private String username;
    private String password;
    private AdminService adminService;

    public AdminLoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
        adminService = new AdminService();
    }

    public Boolean execute() {
        return adminService.checkAuth(username, password);
    }
}
