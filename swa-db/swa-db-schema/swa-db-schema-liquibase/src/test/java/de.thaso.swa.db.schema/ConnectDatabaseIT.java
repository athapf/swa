package de.thaso.swa.db.schema;

import liquibase.exception.LiquibaseException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDatabaseIT {
    public static final Logger LOG = LoggerFactory.getLogger(ConnectDatabaseIT.class);

    @Test
    public void name() throws SQLException, LiquibaseException {
        DatabaseManager databaseManager = new DatabaseManager();

        Properties properties = PropertiesManager.readDevelopProperties();
        databaseManager.createDatabase(properties);


        Connection connection = DriverManager.getConnection(
                properties.getProperty("javax.persistence.jdbc.url"),
                properties.getProperty("javax.persistence.jdbc.user"),
                properties.getProperty("javax.persistence.jdbc.password"));

        ResultSet resultSet = connection.createStatement().executeQuery("select count(1) from department");
        resultSet.next();
        LOG.info("SQL: " + resultSet.getInt(1));

        connection.close();
    }
}
