package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Nurse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NurseMapper implements EntityMapper<Nurse> {
    @Override
    public Nurse extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Nurse.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .surname(resultSet.getString("surname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .build();
    }
}
