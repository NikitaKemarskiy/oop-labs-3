package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.MedicalCardRecordTreatmentDao;
import com.nikita.model.dao.mapper.MedicalCardRecordTreatmentMapper;
import com.nikita.model.dao.query.MedicalCardRecordTreatmentQuery;
import com.nikita.model.entity.MedicalCardRecordTreatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCMedicalCardRecordTreatmentDao implements MedicalCardRecordTreatmentDao {
    private final Connection connection;
    private final MedicalCardRecordTreatmentMapper medicalCardRecordTreatmentMapper;

    public JDBCMedicalCardRecordTreatmentDao(Connection connection) {
        this.connection = connection;
        medicalCardRecordTreatmentMapper = new MedicalCardRecordTreatmentMapper();
    }

    @Override
    public void create (MedicalCardRecordTreatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordTreatmentQuery.CREATE)) {
            statement.setInt(1, entity.getMedicalCardRecord().getId());
            statement.setInt(2, entity.getTreatment().getId());
            statement.setInt(3, entity.getAmount());
            statement.setInt(4, entity.getAmountLeft());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<MedicalCardRecordTreatment> findById(int id) {
        MedicalCardRecordTreatment medicalCardRecordTreatment = null;
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordTreatmentQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medicalCardRecordTreatment = medicalCardRecordTreatmentMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(medicalCardRecordTreatment);
    }

    @Override
    public List<MedicalCardRecordTreatment> findAll() {
        List<MedicalCardRecordTreatment> medicalCardsTreatments = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MedicalCardRecordTreatmentQuery.FIND_ALL);
            while (resultSet.next()) {
                medicalCardsTreatments.add(medicalCardRecordTreatmentMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return medicalCardsTreatments;
    }

    @Override
    public void update(MedicalCardRecordTreatment entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordTreatmentQuery.UPDATE)) {
            statement.setInt(1, entity.getAmountLeft());
            statement.setInt(2, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordTreatmentQuery.DELETE)) {
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
