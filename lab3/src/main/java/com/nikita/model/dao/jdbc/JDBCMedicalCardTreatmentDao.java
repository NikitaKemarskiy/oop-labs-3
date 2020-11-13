package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.MedicalCardTreatmentDao;
import com.nikita.model.dao.mapper.MedicalCardTreatmentMapper;
import com.nikita.model.dao.query.MedicalCardTreatmentQuery;
import com.nikita.model.entity.MedicalCardTreatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCMedicalCardTreatmentDao implements MedicalCardTreatmentDao {
    private final Connection connection;
    private final MedicalCardTreatmentMapper medicalCardTreatmentMapper;

    public JDBCMedicalCardTreatmentDao(Connection connection) {
        this.connection = connection;
        medicalCardTreatmentMapper = new MedicalCardTreatmentMapper();
    }

    @Override
    public void create (MedicalCardTreatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardTreatmentQuery.CREATE)) {
            statement.setInt(1, entity.getMedicalCard().getId());
            statement.setInt(2, entity.getTreatment().getId());
            statement.setInt(3, entity.getAmount());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<MedicalCardTreatment> findById(int id) {
        MedicalCardTreatment medicalCardTreatment = null;
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardTreatmentQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medicalCardTreatment = medicalCardTreatmentMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(medicalCardTreatment);
    }

    @Override
    public List<MedicalCardTreatment> findAll() {
        List<MedicalCardTreatment> medicalCardsTreatments = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MedicalCardTreatmentQuery.FIND_ALL);
            while (resultSet.next()) {
                medicalCardsTreatments.add(medicalCardTreatmentMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return medicalCardsTreatments;
    }

    @Override
    public void update(MedicalCardTreatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardTreatmentQuery.UPDATE)) {
            statement.setInt(1, entity.getMedicalCard().getId());
            statement.setInt(2, entity.getTreatment().getId());
            statement.setInt(3, entity.getAmount());
            statement.setInt(4, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardTreatmentQuery.DELETE)) {
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
