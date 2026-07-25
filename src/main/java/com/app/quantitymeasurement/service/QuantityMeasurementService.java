package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;

import java.util.List;

public interface QuantityMeasurementService {

    // POST Operations
    QuantityMeasurementDTO compare(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO convert(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO add(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO addWithTargetUnit(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO subtract(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO subtractWithTargetUnit(QuantityInputDTO inputDTO);
    QuantityMeasurementDTO divide(QuantityInputDTO inputDTO);

    // GET Queries
    List<QuantityMeasurementDTO> getHistoryByType(String type);
    List<QuantityMeasurementDTO> getHistoryByOperation(String operation);
    List<QuantityMeasurementDTO> getErroredHistory();
    long getCountByOperation(String operation);
}