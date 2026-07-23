package com.bl.quantitymeasurement.service;

import com.bl.quantitymeasurement.dto.QuantityDTO;

public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO convert(QuantityDTO source, QuantityDTO targetUnit);
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit);
    double divide(QuantityDTO q1, QuantityDTO q2);
}