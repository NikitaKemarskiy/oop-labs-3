package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.mapper.AdminMapper;
import com.nikita.model.dao.query.AdminQuery;
import com.nikita.model.entity.Admin;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JDBCAdminDaoTest {

    AdminDao adminDao;
    AdminMapper mockAdminMapper;
    ResultSet mockResultSet;
    Connection mockConnection;
    PreparedStatement mockPreparedStatement;
    Statement mockStatement;

    @Before
    public void setUp() throws Exception {
        mockAdminMapper = mock(AdminMapper.class);
        mockResultSet = mock(ResultSet.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockStatement = mock(Statement.class);
        mockConnection = mock(Connection.class);
        adminDao = new JDBCAdminDao(mockConnection);

        Field adminMapperField = adminDao.getClass().getDeclaredField("adminMapper");
        adminMapperField.setAccessible(true);
        adminMapperField.set(adminDao, mockAdminMapper);
    }

    @Test
    public void create() throws Exception {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        when(mockConnection.prepareStatement(AdminQuery.CREATE)).thenReturn(mockPreparedStatement);

        adminDao.create(admin);

        verify(mockPreparedStatement).setString(1, username);
        verify(mockPreparedStatement).setString(2, password);
        verify(mockPreparedStatement, times(1)).execute();
    }

    @Test
    public void findById() throws Exception {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        when(mockConnection.prepareStatement(AdminQuery.FIND_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockAdminMapper.extractFromResultSet(mockResultSet)).thenReturn(admin);

        Optional<Admin> optionalAdmin = adminDao.findById(id);

        verify(mockPreparedStatement).setInt(1, id);

        assertEquals(optionalAdmin.get(), admin);
    }

    @Test
    public void findAll() throws Exception {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        List<Admin> admins = new ArrayList<>();
        admins.add(admin);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(AdminQuery.FIND_ALL)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockAdminMapper.extractFromResultSet(mockResultSet)).thenReturn(admin);

        assertEquals(adminDao.findAll(), admins);
    }

    @Test
    public void update() throws Exception {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        when(mockConnection.prepareStatement(AdminQuery.UPDATE)).thenReturn(mockPreparedStatement);

        adminDao.update(admin);

        verify(mockPreparedStatement).setString(1, username);
        verify(mockPreparedStatement).setString(2, password);
        verify(mockPreparedStatement).setInt(3, id);
        verify(mockPreparedStatement, times(1)).execute();
    }

    @Test
    public void delete() throws Exception {
        int id = 1;

        when(mockConnection.prepareStatement(AdminQuery.DELETE)).thenReturn(mockPreparedStatement);

        adminDao.delete(id);

        verify(mockPreparedStatement).setInt(1, id);
        verify(mockPreparedStatement, times(1)).execute();
    }

    @Test
    public void findByUsername() throws Exception {
        int id = 1;
        String username = "testUsername";
        String password = "testPassword";
        Admin admin = new Admin(id, username, password);

        when(mockConnection.prepareStatement(AdminQuery.FIND_BY_USERNAME)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockAdminMapper.extractFromResultSet(mockResultSet)).thenReturn(admin);

        Optional<Admin> optionalAdmin = adminDao.findByUsername(username);

        verify(mockPreparedStatement).setString(1, username);

        assertEquals(optionalAdmin.get(), admin);
    }

    @Test
    public void close() throws Exception {
        adminDao.close();

        verify(mockConnection).close();
    }
}