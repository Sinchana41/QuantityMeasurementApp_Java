package com.bl.quantitymeasurement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String operation;
    public String operand1;
    public String operand2;
    public String resultString;
    public double resultValue;
    public boolean isError;
    public String errorMessage;
    public LocalDateTime timestamp;

    public QuantityMeasurementEntity(String operation, String operand1, String operand2, String resultString) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.resultString = resultString;
        this.timestamp = LocalDateTime.now();
        this.isError = false;
    }

    public QuantityMeasurementEntity(String operation, String operand1, String operand2, double resultValue) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.resultValue = resultValue;
        this.resultString = String.valueOf(resultValue);
        this.timestamp = LocalDateTime.now();
        this.isError = false;
    }

    public QuantityMeasurementEntity(String operation, String operand1, String operand2, String errorMessage, boolean isError) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.errorMessage = errorMessage;
        this.isError = isError;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (isError) {
            return "Entity [Op: " + operation + " | Error: " + errorMessage + " | Time: " + timestamp + "]";
        }
        return "Entity [Op: " + operation + " | Op1: " + operand1 + " | Op2: " + operand2 + " | Result: " + resultString + "]";
    }
}