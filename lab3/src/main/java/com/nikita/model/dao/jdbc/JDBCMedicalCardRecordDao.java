package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.MedicalCardRecordDao;
import com.nikita.model.dao.mapper.MedicalCardRecordMapper;
import com.nikita.model.dao.query.MedicalCardRecordQuery;
import com.nikita.model.entity.MedicalCardRecord;

import java.sql.*;
import java.util.*;

public class JDBCMedicalCardRecordDao implements MedicalCardRecordDao {
    private final Connection connection;
    private final MedicalCardRecordMapper medicalCardRecordMapper;

    public JDBCMedicalCardRecordDao(Connection connection) {
        this.connection = connection;
        medicalCardRecordMapper = new MedicalCardRecordMapper();
    }

    @Override
    public void create (MedicalCardRecord entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordQuery.CREATE)) {
            statement.setInt(1, entity.getMedicalCard().getId());
            statement.setString(2, entity.getDiagnosis());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<MedicalCardRecord> findById(int id) {
        MedicalCardRecord medicalCard = null;
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medicalCard = medicalCardRecordMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(medicalCard);
    }

    @Override
    public List<MedicalCardRecord> findAll() {
        List<MedicalCardRecord> medicalCards = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MedicalCardRecordQuery.FIND_ALL);
            while (resultSet.next()) {
                medicalCards.add(medicalCardRecordMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return medicalCards;
    }

    @Override
    public List<MedicalCardRecord> findByPatientIdWithRelations(int patientId) {
        Map<Integer, MedicalCardRecord> medicalCardRecordMap = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordQuery.FIND_BY_PATIENT_ID_WITH_RELATIONS)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medicalCardRecordMapper.extractFromResultSetWithRelations(resultSet, medicalCardRecordMap);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return new ArrayList<>(medicalCardRecordMap.values());
    }

    @Override
    public void update(MedicalCardRecord entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordQuery.UPDATE)) {
            statement.setString(1, entity.getDiagnosis());
            statement.setInt(2, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardRecordQuery.DELETE)) {
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
