package com.nikita.model.service;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.jdbc.JDBCAdminDao;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;
import com.nikita.model.entity.Admin;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminServiceTest {

    AdminService adminService;
    DaoFactory mockDaoFactory;
    AdminDao mockAdminDao;

    @Before
    public void setUp() throws Exception {
        mockDaoFactory = mock(JDBCDaoFactory.class);
        mockAdminDao = mock(JDBCAdminDao.class);
        adminService = new AdminService();

        Field daoFactoryField = adminService.getClass().getDeclaredField("daoFactory");
        daoFactoryField.setAccessible(true);
        daoFactoryField.set(adminService, mockDaoFactory);
    }

    @Test
    public void checkAuth() {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        when(mockDaoFactory.createAdminDao()).thenReturn(mockAdminDao);
        when(mockAdminDao.findByUsername(username)).thenReturn(Optional.of(admin));

        assertTrue(adminService.checkAuth(username, password));
    }
}