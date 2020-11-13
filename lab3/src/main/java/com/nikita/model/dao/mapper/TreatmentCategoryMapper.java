package com.nikita.model.dao.mapper;

import com.nikita.model.entity.TreatmentCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentCategoryMapper implements EntityMapper<TreatmentCategory> {
    @Override
    public TreatmentCategory extractFromResultSet(ResultSet resultSet) throws SQLException {
        return TreatmentCategory.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .build();
    }
}
