package com.nikita.model.dao.jdbc;

import com.nikita.model.dao.NurseDao;
import com.nikita.model.dao.mapper.NurseMapper;
import com.nikita.model.dao.query.NurseQuery;
import com.nikita.model.entity.Nurse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCNurseDao implements NurseDao {
    private final Connection connection;
    private final NurseMapper nurseMapper;

    public JDBCNurseDao(Connection connection) {
        this.connection = connection;
        nurseMapper = new NurseMapper();
    }

    @Override
    public void create (Nurse entity) {
        try (PreparedStatement statement = connection.prepareStatement(NurseQuery.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public Optional<Nurse> findById(int id) {
        Nurse nurse = null;
        try (PreparedStatement statement = connection.prepareStatement(NurseQuery.FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nurse = nurseMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return Optional.ofNullable(nurse);
    }

    @Override
    public List<Nurse> findAll() {
        List<Nurse> nurses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(NurseQuery.FIND_ALL);
            while (resultSet.next()) {
                nurses.add(nurseMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException err) {
            System.err.println(err);
        }
        return nurses;
    }

    @Override
    public void update(Nurse entity) {
        try (PreparedStatement statement = connection.prepareStatement(NurseQuery.UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setObject(3, entity.getBirthday());
            statement.setInt(4, entity.getId());
            statement.execute();
        } catch (SQLException err) {
            System.err.println(err);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(NurseQuery.DELETE)) {
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
