package com.app.quantitymeasurement.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInputDTO {

    // Required for ALL operations
    @Valid
    private QuantityDTO thisQuantityDTO;

    // Optional: Only needed for 2-operand operations (Compare, Add, Subtract, Divide)
    @Valid
    private QuantityDTO thatQuantityDTO;

    // Optional: Only needed for Convert or *-with-target-unit operations
    private String targetUnit;
}