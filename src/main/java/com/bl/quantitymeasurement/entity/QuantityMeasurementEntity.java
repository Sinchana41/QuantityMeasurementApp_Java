package com.bl.quantitymeasurement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    public String operation;
    public String operand1;
    public String operand2;
    public String resultString;
    public double resultValue;
    public boolean isError;
    public String errorMessage;
    public LocalDateTime timestamp;

    public QuantityMeasurementEntity() {
        this.timestamp = LocalDateTime.now();
    }

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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        if (isError) {
            return "Entity [Op: " + operation + " | Error: " + errorMessage + " | Time: " + timestamp + "]";
        }
        return "Entity [Op: " + operation + " | Op1: " + operand1 + " | Op2: " + operand2 + " | Result: " + resultString + "]";
    }
}