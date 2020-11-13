package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.PatientDao;
import com.nikita.model.dao.mapper.PatientMapper;
import com.nikita.model.dao.query.PatientQuery;
import com.nikita.model.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPatientDao implements PatientDao {
    private final Connection connection;
    private final PatientMapper patientMapper;

    public JDBCPatientDao(Connection connection) {
        this.connection = connection;
        patientMapper = new PatientMapper();
    }

    @Override
    public void create (Patient entity) {
        try (PreparedStatement statement = connection.prepareStatement(PatientQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.setInt(4, entity.getDoctor().getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Patient> findById(int id) {
        Patient patient = null;
        try (PreparedStatement statement = connection.prepareStatement(PatientQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patient = patientMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(PatientQuery.FIND_ALL);
            while (resultSet.next()) {
                patients.add(patientMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return patients;
    }

    @Override
    public void update(Patient entity) {
        try (PreparedStatement statement = connection.prepareStatement(PatientQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.setInt(4, entity.getDoctor().getId());
            statement.setInt(5, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(PatientQuery.DELETE)) {
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
