package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.QuantityMeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementService service;

    // --- 1. SUBTRACT ---
    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.subtract(inputDTO));
    }

    // --- 2. SUBTRACT WITH TARGET UNIT ---
    @PostMapping("/subtract-with-target-unit")
    public ResponseEntity<QuantityMeasurementDTO> subtractWithTargetUnit(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.subtractWithTargetUnit(inputDTO));
    }

    // --- 3. DIVIDE ---
    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.divide(inputDTO));
    }

    // --- 4. CONVERT ---
    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.convert(inputDTO));
    }

    // --- 5. COMPARE ---
    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.compare(inputDTO));
    }

    // --- 6. ADD ---
    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.add(inputDTO));
    }

    // --- 7. ADD WITH TARGET UNIT ---
    @PostMapping("/add-with-target-unit")
    public ResponseEntity<QuantityMeasurementDTO> addWithTargetUnit(@Valid @RequestBody QuantityInputDTO inputDTO) {
        return ResponseEntity.ok(service.addWithTargetUnit(inputDTO));
    }

    // --- 8. HISTORY BY TYPE ---
    @GetMapping("/history/type/{type}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getHistoryByType(type));
    }

    // --- 9. HISTORY BY OPERATION ---
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(@PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    // --- 10. ERRORED HISTORY ---
    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErroredHistory() {
        return ResponseEntity.ok(service.getErroredHistory());
    }

    // --- 11. OPERATION COUNT ---
    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getCountByOperation(operation));
    }
}