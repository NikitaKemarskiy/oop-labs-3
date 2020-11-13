package com.nikita.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {
    T extractFromResultSet(ResultSet rs) throws SQLException;
    // T makeUnique(Map<int, T> cache, T entity);
}