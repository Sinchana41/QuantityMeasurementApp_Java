package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements", indexes = {
        @Index(name = "idx_operation", columnList = "operation"),
        @Index(name = "idx_measurement_type", columnList = "this_measurement_type")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // First Quantity (Required)
    @Column(name = "this_value", nullable = false)
    private double thisValue;

    @Column(name = "this_unit", nullable = false)
    private String thisUnit;

    @Column(name = "this_measurement_type", nullable = false)
    private String thisMeasurementType;

    // Second Quantity (Optional - Set nullable = true)
    @Column(name = "that_value", nullable = true)
    private Double thatValue; // Changed from double to Double wrapper object to allow null

    @Column(name = "that_unit", nullable = true)
    private String thatUnit;

    @Column(name = "that_measurement_type", nullable = true)
    private String thatMeasurementType;

    // Operation Details
    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "result_string")
    private String resultString;

    // Error Tracking Status
    @Column(name = "is_error", nullable = false)
    private boolean isError;

    @Column(name = "error_message")
    private String errorMessage;

    // Timestamps
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}