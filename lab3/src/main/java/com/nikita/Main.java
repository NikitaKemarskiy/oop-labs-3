package com.nikita;

import com.nikita.model.connection.ConnectionPoolHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        DataSource dataSource = ConnectionPoolHandler.getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            System.out.println(connection.getCatalog());
        } catch (SQLException err) {
            System.err.println(err);
        }
    }
}
