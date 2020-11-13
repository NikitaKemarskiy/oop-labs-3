package com.nikita.model.dao;

import com.nikita.model.entity.Admin;

import java.util.Optional;

public interface AdminDao extends GenericDao<Admin> {
    Optional<Admin> findByUsername(String username);
}
