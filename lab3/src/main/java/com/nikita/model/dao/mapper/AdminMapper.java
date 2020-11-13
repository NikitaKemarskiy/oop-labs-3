package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements EntityMapper<Admin> {
    @Override
    public Admin extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Admin.builder()
            .id(resultSet.getInt("id"))
            .username(resultSet.getString("username"))
            .password(resultSet.getString("password"))
            .build();
    }
}
