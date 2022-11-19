package com.github.bogdan.databaseConfiguration;

import com.github.bogdan.models.Journal;
import com.github.bogdan.models.Student;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseConfiguration {
    private ConnectionSource connectionSource;

    public DatabaseConfiguration(String jdbcConnectionString) {
        try{
            connectionSource = new JdbcConnectionSource(jdbcConnectionString);
            TableUtils.createTableIfNotExists(connectionSource, Student.class);
            TableUtils.createTableIfNotExists(connectionSource, Journal.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
