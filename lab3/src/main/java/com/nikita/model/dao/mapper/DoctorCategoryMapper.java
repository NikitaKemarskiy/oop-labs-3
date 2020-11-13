package com.nikita.model.dao.mapper;

import com.nikita.model.entity.DoctorCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorCategoryMapper implements EntityMapper<DoctorCategory> {
    @Override
    public DoctorCategory extractFromResultSet(ResultSet resultSet) throws SQLException {
        return DoctorCategory.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .build();
    }
}