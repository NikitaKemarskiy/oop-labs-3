package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.MedicalCardDao;
import com.nikita.model.dao.mapper.MedicalCardMapper;
import com.nikita.model.dao.query.MedicalCardQuery;
import com.nikita.model.entity.MedicalCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCMedicalCardDao implements MedicalCardDao {
    private final Connection connection;
    private final MedicalCardMapper medicalCardMapper;

    public JDBCMedicalCardDao(Connection connection) {
        this.connection = connection;
        medicalCardMapper = new MedicalCardMapper();
    }

    @Override
    public void create (MedicalCard entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardQuery.CREATE)) {
            statement.setInt(1, entity.getPatient().getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<MedicalCard> findById(int id) {
        MedicalCard medicalCard = null;
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                medicalCard = medicalCardMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(medicalCard);
    }

    @Override
    public List<MedicalCard> findAll() {
        List<MedicalCard> medicalCards = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(MedicalCardQuery.FIND_ALL);
            while (resultSet.next()) {
                medicalCards.add(medicalCardMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return medicalCards;
    }

    @Override
    public void update(MedicalCard entity) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardQuery.UPDATE)) {
            statement.setInt(1, entity.getPatient().getId());
            statement.setInt(2, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(MedicalCardQuery.DELETE)) {
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
