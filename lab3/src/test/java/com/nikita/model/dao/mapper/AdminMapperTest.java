package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Admin;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminMapperTest {

    private int id = 1;
    private String username = "testUsername";
    private String password = "testPassword";
    AdminMapper adminMapper;
    Admin admin;

    @Before
    public void setUp() throws Exception {
        adminMapper = new AdminMapper();
        admin = new Admin(id, username, password);
    }

    @Test
    public void extractFromResultSet() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("username")).thenReturn(username);
        when(resultSet.getString("password")).thenReturn(password);

        Admin admin = adminMapper.extractFromResultSet(resultSet);

        assertEquals(this.admin, admin);
    }
}