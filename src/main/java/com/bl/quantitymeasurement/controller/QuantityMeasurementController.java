package com.bl.quantitymeasurement.controller;

import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public boolean performComparison(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO performConversion(QuantityDTO source, QuantityDTO targetUnit) {
        return service.convert(source, targetUnit);
    }

    public QuantityDTO performAddition(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }

    public QuantityDTO performAddition(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit) {
        return service.add(q1, q2, targetUnit);
    }

    public QuantityDTO performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1, q2);
    }

    public double performDivision(QuantityDTO q1, QuantityDTO q2) {
        return service.divide(q1, q2);
    }
}