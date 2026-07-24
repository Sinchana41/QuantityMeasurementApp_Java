package com.bl.quantitymeasurement.util;

import com.bl.quantitymeasurement.exception.DatabaseException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    private static HikariDataSource dataSource;

    static {
        try {
            ApplicationConfig config = ApplicationConfig.getInstance();
            HikariConfig hikariConfig = new HikariConfig();

            hikariConfig.setJdbcUrl(config.getProperty("db.url", "jdbc:h2:./quantitymeasurementdb;AUTO_SERVER=TRUE"));
            hikariConfig.setUsername(config.getProperty("db.username", "sa"));
            hikariConfig.setPassword(config.getProperty("db.password", ""));
            hikariConfig.setDriverClassName(config.getProperty("db.driver", "org.h2.Driver"));

            hikariConfig.setMaximumPoolSize(config.getIntProperty("db.hikari.maximum-pool-size", 10));
            hikariConfig.setMinimumIdle(config.getIntProperty("db.hikari.minimum-idle", 2));
            hikariConfig.setConnectionTimeout(config.getLongProperty("db.hikari.connection-timeout", 30000));
            hikariConfig.setIdleTimeout(config.getLongProperty("db.hikari.idle-timeout", 600000));
            hikariConfig.setMaxLifetime(config.getLongProperty("db.hikari.max-lifetime", 1800000));
            hikariConfig.setPoolName(config.getProperty("db.hikari.pool-name", "QuantityMeasurementPool"));
            hikariConfig.setConnectionTestQuery(config.getProperty("db.hikari.connection-test-query", "SELECT 1"));

            dataSource = new HikariDataSource(hikariConfig);
            initializeDatabaseSchema();
        } catch (Exception e) {
            throw DatabaseException.connectionFailed("Failed to initialize HikariCP connection pool.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static void initializeDatabaseSchema() {
        try (Connection conn = getConnection();
             InputStream is = ConnectionPool.class.getClassLoader().getResourceAsStream("db/schema.sql")) {

            if (is == null) return;
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
                 Statement stmt = conn.createStatement()) {

                StringBuilder sqlBuilder = new StringBuilder();
                char[] buffer = new char[1024];
                int charsRead;
                while ((charsRead = reader.read(buffer)) != -1) {
                    sqlBuilder.append(buffer, 0, charsRead);
                }

                for (String sql : sqlBuilder.toString().split(";")) {
                    if (!sql.trim().isEmpty()) {
                        stmt.execute(sql.trim());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: Schema initialization failed: " + e.getMessage());
        }
    }

    public static void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}