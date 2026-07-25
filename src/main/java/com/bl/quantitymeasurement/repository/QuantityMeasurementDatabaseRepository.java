package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.bl.quantitymeasurement.exception.DatabaseException;
import com.bl.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        if (entity.getId() == null) {
            return insert(entity);
        } else {
            return update(entity);
        }
    }

    private QuantityMeasurementEntity insert(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement_entity ("
                + "operation, operand1, operand2, result_string, result_value, "
                + "is_error, error_message, timestamp"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatementParameters(stmt, entity);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }

            return entity;
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
    }

    private QuantityMeasurementEntity update(QuantityMeasurementEntity entity) {
        String sql = "UPDATE quantity_measurement_entity SET "
                + "operation = ?, operand1 = ?, operand2 = ?, result_string = ?, result_value = ?, "
                + "is_error = ?, error_message = ?, timestamp = ? "
                + "WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setPreparedStatementParameters(stmt, entity);
            stmt.setLong(9, entity.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DatabaseException("Failed to update measurement, entity with ID " + entity.getId() + " not found.");
            }

            return entity;
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
    }

    @Override
    public Optional<QuantityMeasurementEntity> findById(Long id) {
        String sql = "SELECT * FROM quantity_measurement_entity WHERE id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
        return Optional.empty();
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        String sql = "SELECT * FROM quantity_measurement_entity ORDER BY id ASC";
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
        return list;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM quantity_measurement_entity WHERE id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "TRUNCATE TABLE quantity_measurement_entity";
        try (Connection conn = ConnectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
    }

    private void setPreparedStatementParameters(PreparedStatement stmt, QuantityMeasurementEntity entity) throws SQLException {
        stmt.setString(1, entity.getOperation());
        stmt.setString(2, entity.getOperand1());
        stmt.setString(3, entity.getOperand2());
        stmt.setString(4, entity.getResultString());
        stmt.setDouble(5, entity.getResultValue());
        stmt.setBoolean(6, entity.isError());
        stmt.setString(7, entity.getErrorMessage());

        LocalDateTime time = entity.getTimestamp() != null ? entity.getTimestamp() : LocalDateTime.now();
        stmt.setTimestamp(8, Timestamp.valueOf(time));
    }

    private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setId(rs.getLong("id"));
        entity.setOperation(rs.getString("operation"));
        entity.setOperand1(rs.getString("operand1"));
        entity.setOperand2(rs.getString("operand2"));
        entity.setResultString(rs.getString("result_string"));
        entity.setResultValue(rs.getDouble("result_value"));
        entity.setError(rs.getBoolean("is_error"));
        entity.setErrorMessage(rs.getString("error_message"));

        Timestamp ts = rs.getTimestamp("timestamp");
        if (ts != null) {
            entity.setTimestamp(ts.toLocalDateTime());
        }

        return entity;
    }
}