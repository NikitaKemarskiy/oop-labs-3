package com.nikita.model.service;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Admin;

import java.util.Optional;

public class AdminService {
    private DaoFactory daoFactory;

    private boolean checkPassword(String password, String passwordChecked) {
        return password.equals(passwordChecked);
    }

    public AdminService() {
        daoFactory = new JDBCDaoFactory();
    }

    public boolean checkAuth(String username, String password) {
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            Optional<Admin> adminOptional = adminDao.findByUsername(username);
            if (adminOptional.isEmpty()) {
                return false;
            }
            Admin admin = adminOptional.get();
            return checkPassword(admin.getPassword(), password);
        }
    }
}
