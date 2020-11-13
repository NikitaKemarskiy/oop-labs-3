package com.nikita.model.dao.mapper;

import com.nikita.model.entity.Treatment;
import com.nikita.model.entity.TreatmentCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentMapper implements EntityMapper<Treatment> {
    @Override
    public Treatment extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Treatment.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .category(TreatmentCategory.builder().id(resultSet.getInt("categoryId")).build())
            .build();
    }
}