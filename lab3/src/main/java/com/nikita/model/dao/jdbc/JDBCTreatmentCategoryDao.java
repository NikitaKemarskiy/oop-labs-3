package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.TreatmentCategoryDao;
import com.nikita.model.dao.mapper.TreatmentCategoryMapper;
import com.nikita.model.dao.query.TreatmentCategoryQuery;
import com.nikita.model.entity.TreatmentCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTreatmentCategoryDao implements TreatmentCategoryDao {
    private final Connection connection;
    private final TreatmentCategoryMapper treatmentCategoryMapper;

    public JDBCTreatmentCategoryDao(Connection connection) {
        this.connection = connection;
        treatmentCategoryMapper = new TreatmentCategoryMapper();
    }

    @Override
    public void create (TreatmentCategory entity) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentCategoryQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<TreatmentCategory> findById(int id) {
        TreatmentCategory treatmentCategory = null;
        try (PreparedStatement statement = connection.prepareStatement(TreatmentCategoryQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                treatmentCategory = treatmentCategoryMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(treatmentCategory);
    }

    @Override
    public List<TreatmentCategory> findAll() {
        List<TreatmentCategory> treatmentsCategories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(TreatmentCategoryQuery.FIND_ALL);
            while (resultSet.next()) {
                treatmentsCategories.add(treatmentCategoryMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return treatmentsCategories;
    }

    @Override
    public void update(TreatmentCategory entity) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentCategoryQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(TreatmentCategoryQuery.DELETE)) {
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
