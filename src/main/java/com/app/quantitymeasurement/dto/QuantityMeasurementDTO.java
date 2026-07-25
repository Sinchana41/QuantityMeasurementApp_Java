package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {

    private Long id;

    // First Quantity
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    // Second Quantity (Use Double wrapper object, NOT primitive double)
    private Double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;
    private String resultString;

    private boolean isError;
    private String errorMessage;
    private LocalDateTime createdAt;

    // Mapper method with safe null checks
    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {
        if (entity == null) return null;

        return new QuantityMeasurementDTO(
                entity.getId(),
                entity.getThisValue(),
                entity.getThisUnit(),
                entity.getThisMeasurementType(),
                entity.getThatValue(), // Now safely handles null without throwing NPE
                entity.getThatUnit(),
                entity.getThatMeasurementType(),
                entity.getOperation(),
                entity.getResultString(),
                entity.isError(),
                entity.getErrorMessage(),
                entity.getCreatedAt()
        );
    }

    public static List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .collect(Collectors.toList());
    }
}