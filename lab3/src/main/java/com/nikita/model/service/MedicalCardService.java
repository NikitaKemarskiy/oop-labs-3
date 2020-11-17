package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.jdbc.JDBCDaoFactory;

public class MedicalCardService {
    private DaoFactory daoFactory;

    public MedicalCardService() {
        daoFactory = new JDBCDaoFactory();
    }
}
