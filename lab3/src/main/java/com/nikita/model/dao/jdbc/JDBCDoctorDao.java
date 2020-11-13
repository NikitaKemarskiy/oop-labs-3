package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.DoctorDao;
import com.nikita.model.dao.mapper.DoctorMapper;
import com.nikita.model.dao.query.DoctorCategoryQuery;
import com.nikita.model.dao.query.DoctorQuery;
import com.nikita.model.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JDBCDoctorDao implements DoctorDao {
    private final Connection connection;
    private final DoctorMapper doctorMapper;

    public JDBCDoctorDao(Connection connection) {
        this.connection = connection;
        doctorMapper = new DoctorMapper();
    }

    @Override
    public void create (Doctor entity) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.setInt(4, entity.getCategory().getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Doctor> findById(int id) {
        Doctor doctor = null;
        try (PreparedStatement statement = connection.prepareStatement(DoctorQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doctor = doctorMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(DoctorQuery.FIND_ALL);
            while (resultSet.next()) {
                doctors.add(doctorMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return doctors;
    }

    @Override
    public void update(Doctor entity) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.setInt(4, entity.getCategory().getId());
            statement.setInt(5, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DoctorQuery.DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public List<Doctor> findAllWithRelations() {
        List<Doctor> doctors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(DoctorQuery.FIND_ALL_WITH_RELATIONS);
            while (resultSet.next()) {
                doctors.add(doctorMapper.extractFromResultSetWithRelations(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return doctors;
    }

    @Override
    public List<Doctor> findByCategoriesWithRelations(int[] categories) {
        List<Doctor> doctors = new ArrayList<>();
        String[] paramsArr = new String[categories.length];
        Arrays.fill(paramsArr, "?");
        String sql = String.format(
            DoctorQuery.FIND_BY_CATEGORIES_WITH_RELATIONS,
            String.join(",", paramsArr)
        );

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < categories.length; i++) {
                statement.setInt(i + 1, categories[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doctors.add(doctorMapper.extractFromResultSetWithRelations(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return doctors;
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
