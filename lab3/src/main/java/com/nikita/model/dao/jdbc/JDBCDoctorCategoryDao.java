package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.DoctorCategoryDao;
import com.nikita.model.dao.mapper.DoctorCategoryMapper;
import com.nikita.model.dao.query.DoctorCategoryQuery;
import com.nikita.model.entity.DoctorCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDoctorCategoryDao implements DoctorCategoryDao {
    private final Connection connection;
    private final DoctorCategoryMapper doctorCategoryMapper;

    public JDBCDoctorCategoryDao(Connection connection) {
        this.connection = connection;
        doctorCategoryMapper = new DoctorCategoryMapper();
    }

    @Override
    public void create (DoctorCategory entity) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorCategoryQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<DoctorCategory> findById(int id) {
        DoctorCategory doctorCategory = null;
        try (PreparedStatement statement = connection.prepareStatement(DoctorCategoryQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doctorCategory = doctorCategoryMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(doctorCategory);
    }

    @Override
    public List<DoctorCategory> findAll() {
        List<DoctorCategory> doctorsCategories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(DoctorCategoryQuery.FIND_ALL);
            while (resultSet.next()) {
                doctorsCategories.add(doctorCategoryMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return doctorsCategories;
    }

    @Override
    public void update(DoctorCategory entity) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorCategoryQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorCategoryQuery.DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
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
