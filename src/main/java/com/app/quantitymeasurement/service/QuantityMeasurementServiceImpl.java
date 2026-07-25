package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.util.UnitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    @Transactional
    public QuantityMeasurementDTO compare(QuantityInputDTO inputDTO) {
        validateMatchingCategory(inputDTO, "COMPARE");

        double base1 = UnitConverter.convertToBase(
                inputDTO.getThisQuantityDTO().getValue(),
                inputDTO.getThisQuantityDTO().getUnit(),
                inputDTO.getThisQuantityDTO().getMeasurementType()
        );

        double base2 = UnitConverter.convertToBase(
                inputDTO.getThatQuantityDTO().getValue(),
                inputDTO.getThatQuantityDTO().getUnit(),
                inputDTO.getThatQuantityDTO().getMeasurementType()
        );

        boolean isEqual = Math.abs(base1 - base2) < 0.001;
        String resultStr = isEqual ? "EQUAL" : "NOT_EQUAL";

        return saveAndMap(inputDTO, "COMPARE", resultStr, false, null);
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO convert(QuantityInputDTO inputDTO) {
        QuantityDTO source = inputDTO.getThisQuantityDTO();
        String targetUnit = inputDTO.getTargetUnit();

        if (targetUnit == null || targetUnit.isBlank()) {
            saveAndMap(inputDTO, "CONVERT", null, true, "Target unit is required for conversion");
            throw new QuantityMeasurementException("Target unit is required for conversion!");
        }

        double baseVal = UnitConverter.convertToBase(source.getValue(), source.getUnit(), source.getMeasurementType());
        double convertedVal = UnitConverter.convertFromBase(baseVal, targetUnit, source.getMeasurementType());

        String resultStr = String.format("%.2f %s", convertedVal, targetUnit);
        return saveAndMap(inputDTO, "CONVERT", resultStr, false, null);
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO add(QuantityInputDTO inputDTO) {
        // Default target unit is the first operand's unit
        inputDTO.setTargetUnit(inputDTO.getThisQuantityDTO().getUnit());
        return performAdd(inputDTO, "ADD");
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO addWithTargetUnit(QuantityInputDTO inputDTO) {
        if (inputDTO.getTargetUnit() == null || inputDTO.getTargetUnit().isBlank()) {
            saveAndMap(inputDTO, "ADD_WITH_TARGET_UNIT", null, true, "Target unit is required");
            throw new QuantityMeasurementException("Target unit is required!");
        }
        return performAdd(inputDTO, "ADD_WITH_TARGET_UNIT");
    }

    private QuantityMeasurementDTO performAdd(QuantityInputDTO inputDTO, String opName) {
        validateMatchingCategory(inputDTO, opName);

        if ("TEMPERATURE".equalsIgnoreCase(inputDTO.getThisQuantityDTO().getMeasurementType())) {
            saveAndMap(inputDTO, opName, null, true, "Addition not supported for Temperature");
            throw new QuantityMeasurementException("Addition is not supported for Temperature!");
        }

        double base1 = UnitConverter.convertToBase(inputDTO.getThisQuantityDTO().getValue(), inputDTO.getThisQuantityDTO().getUnit(), inputDTO.getThisQuantityDTO().getMeasurementType());
        double base2 = UnitConverter.convertToBase(inputDTO.getThatQuantityDTO().getValue(), inputDTO.getThatQuantityDTO().getUnit(), inputDTO.getThatQuantityDTO().getMeasurementType());

        double sumBase = base1 + base2;
        double finalResult = UnitConverter.convertFromBase(sumBase, inputDTO.getTargetUnit(), inputDTO.getThisQuantityDTO().getMeasurementType());

        return saveAndMap(inputDTO, opName, String.format("%.2f %s", finalResult, inputDTO.getTargetUnit()), false, null);
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO subtract(QuantityInputDTO inputDTO) {
        inputDTO.setTargetUnit(inputDTO.getThisQuantityDTO().getUnit());
        return performSubtract(inputDTO, "SUBTRACT");
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO subtractWithTargetUnit(QuantityInputDTO inputDTO) {
        if (inputDTO.getTargetUnit() == null || inputDTO.getTargetUnit().isBlank()) {
            saveAndMap(inputDTO, "SUBTRACT_WITH_TARGET_UNIT", null, true, "Target unit is required");
            throw new QuantityMeasurementException("Target unit is required!");
        }
        return performSubtract(inputDTO, "SUBTRACT_WITH_TARGET_UNIT");
    }

    private QuantityMeasurementDTO performSubtract(QuantityInputDTO inputDTO, String opName) {
        validateMatchingCategory(inputDTO, opName);

        if ("TEMPERATURE".equalsIgnoreCase(inputDTO.getThisQuantityDTO().getMeasurementType())) {
            saveAndMap(inputDTO, opName, null, true, "Subtraction not supported for Temperature");
            throw new QuantityMeasurementException("Subtraction is not supported for Temperature!");
        }

        double base1 = UnitConverter.convertToBase(inputDTO.getThisQuantityDTO().getValue(), inputDTO.getThisQuantityDTO().getUnit(), inputDTO.getThisQuantityDTO().getMeasurementType());
        double base2 = UnitConverter.convertToBase(inputDTO.getThatQuantityDTO().getValue(), inputDTO.getThatQuantityDTO().getUnit(), inputDTO.getThatQuantityDTO().getMeasurementType());

        double diffBase = base1 - base2;
        double finalResult = UnitConverter.convertFromBase(diffBase, inputDTO.getTargetUnit(), inputDTO.getThisQuantityDTO().getMeasurementType());

        return saveAndMap(inputDTO, opName, String.format("%.2f %s", finalResult, inputDTO.getTargetUnit()), false, null);
    }

    @Override
    @Transactional
    public QuantityMeasurementDTO divide(QuantityInputDTO inputDTO) {
        validateMatchingCategory(inputDTO, "DIVIDE");

        if (inputDTO.getThatQuantityDTO().getValue() == 0) {
            saveAndMap(inputDTO, "DIVIDE", null, true, "Division by zero is not allowed");
            throw new QuantityMeasurementException("Division by zero is not allowed!");
        }

        double base1 = UnitConverter.convertToBase(inputDTO.getThisQuantityDTO().getValue(), inputDTO.getThisQuantityDTO().getUnit(), inputDTO.getThisQuantityDTO().getMeasurementType());
        double base2 = UnitConverter.convertToBase(inputDTO.getThatQuantityDTO().getValue(), inputDTO.getThatQuantityDTO().getUnit(), inputDTO.getThatQuantityDTO().getMeasurementType());

        double ratio = base1 / base2;
        return saveAndMap(inputDTO, "DIVIDE", String.format("%.2f", ratio), false, null);
    }

    // --- GET History & Count Methods ---
    @Override
    public List<QuantityMeasurementDTO> getHistoryByType(String type) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementType(type));
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByOperation(operation.toUpperCase()));
    }

    @Override
    public List<QuantityMeasurementDTO> getErroredHistory() {
        return QuantityMeasurementDTO.fromEntityList(repository.findByIsErrorTrue());
    }

    @Override
    public long getCountByOperation(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toUpperCase());
    }

    // --- Helpers ---
    private void validateMatchingCategory(QuantityInputDTO inputDTO, String op) {
        if (!inputDTO.getThisQuantityDTO().getMeasurementType().equalsIgnoreCase(inputDTO.getThatQuantityDTO().getMeasurementType())) {
            String errorMsg = "Cannot perform " + op + " on different measurement types";
            saveAndMap(inputDTO, op, null, true, errorMsg);
            throw new QuantityMeasurementException(errorMsg);
        }
    }

    private void validateTwoOperands(QuantityInputDTO inputDTO, String opName) {
        if (inputDTO.getThisQuantityDTO() == null || inputDTO.getThatQuantityDTO() == null) {
            String msg = "Both first and second quantities are required for " + opName;
            saveAndMap(inputDTO, opName, null, true, msg);
            throw new QuantityMeasurementException(msg);
        }
        validateMatchingCategory(inputDTO, opName);
    }

    private QuantityMeasurementDTO saveAndMap(QuantityInputDTO inputDTO, String op, String result, boolean isError, String errorMsg) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        if (inputDTO.getThisQuantityDTO() != null) {
            entity.setThisValue(inputDTO.getThisQuantityDTO().getValue());
            entity.setThisUnit(inputDTO.getThisQuantityDTO().getUnit());
            entity.setThisMeasurementType(inputDTO.getThisQuantityDTO().getMeasurementType());
        }
        if (inputDTO.getThatQuantityDTO() != null) {
            entity.setThatValue(inputDTO.getThatQuantityDTO().getValue());
            entity.setThatUnit(inputDTO.getThatQuantityDTO().getUnit());
            entity.setThatMeasurementType(inputDTO.getThatQuantityDTO().getMeasurementType());
        }
        entity.setOperation(op);
        entity.setResultString(result);
        entity.setError(isError);
        entity.setErrorMessage(errorMsg);

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }
}