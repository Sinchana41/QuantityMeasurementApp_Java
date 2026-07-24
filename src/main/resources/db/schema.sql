DROP TABLE IF EXISTS quantity_measurement_entity CASCADE;

CREATE TABLE quantity_measurement_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    operand1 VARCHAR(255),
    operand2 VARCHAR(255),
    result_string VARCHAR(255),
    result_value DOUBLE DEFAULT 0.0,
    is_error BOOLEAN DEFAULT FALSE,
    error_message VARCHAR(500),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);