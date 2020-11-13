package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.TreatmentDao;
import com.nikita.model.dao.mapper.TreatmentMapper;
import com.nikita.model.dao.query.TreatmentQuery;
import com.nikita.model.entity.Treatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTreatmentDao implements TreatmentDao {
    private final Connection connection;
    private final TreatmentMapper treatmentMapper;

    public JDBCTreatmentDao(Connection connection) {
        this.connection = connection;
        treatmentMapper = new TreatmentMapper();
    }

    @Override
    public void create (Treatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCategory().getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Treatment> findById(int id) {
        Treatment treatment = null;
        try (PreparedStatement statement = connection.prepareStatement(TreatmentQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                treatment = treatmentMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(treatment);
    }

    @Override
    public List<Treatment> findAll() {
        List<Treatment> treatments = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(TreatmentQuery.FIND_ALL);
            while (resultSet.next()) {
                treatments.add(treatmentMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return treatments;
    }

    @Override
    public void update(Treatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCategory().getId());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentQuery.DELETE)) {
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
