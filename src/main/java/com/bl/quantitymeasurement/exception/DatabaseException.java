package com.bl.quantitymeasurement.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static DatabaseException connectionFailed(String details, Throwable cause) {
        return new DatabaseException("Database connection failure: " + details, cause);
    }

    public static DatabaseException queryFailed(String sql, Throwable cause) {
        return new DatabaseException("Failed executing query [" + sql + "]", cause);
    }
}