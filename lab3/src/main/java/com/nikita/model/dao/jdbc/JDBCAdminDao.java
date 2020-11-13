package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.AdminDao;
import com.nikita.model.dao.mapper.AdminMapper;
import com.nikita.model.dao.query.AdminQuery;
import com.nikita.model.entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCAdminDao implements AdminDao {
    private final Connection connection;
    private final AdminMapper adminMapper;

    public JDBCAdminDao(Connection connection) {
        this.connection = connection;
        adminMapper = new AdminMapper();
    }

    @Override
    public void create (Admin entity) {
        try (PreparedStatement statement = connection.prepareStatement(AdminQuery.CREATE)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Admin> findById(int id) {
        Admin admin = null;
        try (PreparedStatement statement = connection.prepareStatement(AdminQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                admin = adminMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(AdminQuery.FIND_ALL);
            while (resultSet.next()) {
                admins.add(adminMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return admins;
    }

    @Override
    public void update(Admin entity) {
        try (PreparedStatement statement = connection.prepareStatement(AdminQuery.UPDATE)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(AdminQuery.DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        Admin admin = null;
        try (PreparedStatement statement = connection.prepareStatement(AdminQuery.FIND_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                admin = adminMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException err) {
            System.err.println("XXX Error with closing connection");
            throw new RuntimeException(err);
        }
    }
}
