package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.DischargeDao;
import com.nikita.model.dao.mapper.DischargeMapper;
import com.nikita.model.dao.query.DischargeQuery;
import com.nikita.model.entity.Discharge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDischargeDao implements DischargeDao {
    private final Connection connection;
    private final DischargeMapper dischargeMapper;

    public JDBCDischargeDao(Connection connection) {
        this.connection = connection;
        dischargeMapper = new DischargeMapper();
    }

    @Override
    public void create (Discharge entity) {
        try (PreparedStatement statement = connection.prepareStatement(DischargeQuery.CREATE)) {
            statement.setInt(1, entity.getPatient().getId());
            statement.setString(2, entity.getDiagnosis());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Discharge> findById(int id) {
        Discharge discharge = null;
        try (PreparedStatement statement = connection.prepareStatement(DischargeQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                discharge = dischargeMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(discharge);
    }

    @Override
    public List<Discharge> findAll() {
        List<Discharge> discharges = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(DischargeQuery.FIND_ALL);
            while (resultSet.next()) {
                discharges.add(dischargeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return discharges;
    }

    @Override
    public void update(Discharge entity) {
        try (PreparedStatement statement = connection.prepareStatement(DischargeQuery.UPDATE)) {
            statement.setInt(1, entity.getPatient().getId());
            statement.setString(2, entity.getDiagnosis());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DischargeQuery.DELETE)) {
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
