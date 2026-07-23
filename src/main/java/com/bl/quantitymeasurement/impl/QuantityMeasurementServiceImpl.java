package com.bl.quantitymeasurement.impl;

import com.bl.quantitymeasurement.service.IQuantityMeasurementService;
import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.bl.quantitymeasurement.exception.QuantityMeasurementException;
import com.bl.quantitymeasurement.interfaces.IMeasurable;
import com.bl.quantitymeasurement.model.QuantityModel;
import com.bl.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            validateMatchingTypes(q1, q2);
            QuantityModel<IMeasurable> m1 = getQuantityModel(q1);
            QuantityModel<IMeasurable> m2 = getQuantityModel(q2);

            boolean result = Math.abs(m1.getBaseValue() - m2.getBaseValue()) < 1e-6;
            repository.save(new QuantityMeasurementEntity("COMPARE", q1.toString(), q2.toString(), String.valueOf(result)));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("COMPARE", q1.toString(), q2.toString(), e.getMessage(), true));
            throw new QuantityMeasurementException("Comparison failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, QuantityDTO targetUnit) {
        try {
            validateMatchingTypes(source, targetUnit);
            QuantityModel<IMeasurable> sourceModel = getQuantityModel(source);
            IMeasurable targetMeasurable = getUnitInstance(targetUnit.getUnit(), targetUnit.getMeasurementType());

            double convertedValue = targetMeasurable.convertFromBaseUnit(sourceModel.getBaseValue());
            QuantityDTO result = new QuantityDTO(convertedValue, targetMeasurable);

            repository.save(new QuantityMeasurementEntity("CONVERT", source.toString(), targetUnit.getUnit(), result.toString()));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("CONVERT", source.toString(), targetUnit.getUnit(), e.getMessage(), true));
            throw new QuantityMeasurementException("Conversion failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return add(q1, q2, q1);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit) {
        try {
            validateMatchingTypes(q1, q2);
            validateMatchingTypes(q1, targetUnit);

            QuantityModel<IMeasurable> m1 = getQuantityModel(q1);
            QuantityModel<IMeasurable> m2 = getQuantityModel(q2);

            double totalBase = m1.getBaseValue() + m2.getBaseValue();
            IMeasurable targetMeasurable = getUnitInstance(targetUnit.getUnit(), targetUnit.getMeasurementType());

            double finalValue = targetMeasurable.convertFromBaseUnit(totalBase);
            QuantityDTO result = new QuantityDTO(finalValue, targetMeasurable);

            repository.save(new QuantityMeasurementEntity("ADD", q1.toString(), q2.toString(), finalValue));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("ADD", q1.toString(), q2.toString(), e.getMessage(), true));
            throw new QuantityMeasurementException("Addition failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return subtract(q1, q2, q1);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit) {
        try {
            validateMatchingTypes(q1, q2);
            validateMatchingTypes(q1, targetUnit);

            QuantityModel<IMeasurable> m1 = getQuantityModel(q1);
            QuantityModel<IMeasurable> m2 = getQuantityModel(q2);

            double subBase = m1.getBaseValue() - m2.getBaseValue();
            IMeasurable targetMeasurable = getUnitInstance(targetUnit.getUnit(), targetUnit.getMeasurementType());

            double finalValue = targetMeasurable.convertFromBaseUnit(subBase);
            QuantityDTO result = new QuantityDTO(finalValue, targetMeasurable);

            repository.save(new QuantityMeasurementEntity("SUBTRACT", q1.toString(), q2.toString(), finalValue));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("SUBTRACT", q1.toString(), q2.toString(), e.getMessage(), true));
            throw new QuantityMeasurementException("Subtraction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        try {
            validateMatchingTypes(q1, q2);
            QuantityModel<IMeasurable> m1 = getQuantityModel(q1);
            QuantityModel<IMeasurable> m2 = getQuantityModel(q2);

            if (m2.getBaseValue() == 0) {
                throw new IllegalArgumentException("Division by zero is not allowed.");
            }

            double result = m1.getBaseValue() / m2.getBaseValue();
            repository.save(new QuantityMeasurementEntity("DIVIDE", q1.toString(), q2.toString(), result));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity("DIVIDE", q1.toString(), q2.toString(), e.getMessage(), true));
            throw new QuantityMeasurementException("Division failed: " + e.getMessage(), e);
        }
    }

    // --- HELPER METHODS ---

    private void validateMatchingTypes(QuantityDTO q1, QuantityDTO q2) {
        if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
            throw new IllegalArgumentException("Incompatible types: " + q1.getMeasurementType() + " and " + q2.getMeasurementType());
        }
    }

    private QuantityModel<IMeasurable> getQuantityModel(QuantityDTO dto) {
        IMeasurable unit = getUnitInstance(dto.getUnit(), dto.getMeasurementType());
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private IMeasurable getUnitInstance(String unitName, String measurementType) {
        try {
            Class<?> clazz = Class.forName("com.bl.quantitymeasurement.dto.QuantityDTO$" + measurementType);
            for (Object enumConstant : clazz.getEnumConstants()) {
                Enum<?> enumVal = (Enum<?>) enumConstant;
                if (enumVal.name().equalsIgnoreCase(unitName)) {
                    return (IMeasurable) enumConstant;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid unit: " + unitName + " for type: " + measurementType, e);
        }
        throw new IllegalArgumentException("Unit not found: " + unitName + " in " + measurementType);
    }
}