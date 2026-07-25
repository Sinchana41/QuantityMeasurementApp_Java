package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    List<QuantityMeasurementEntity> findByIsErrorTrue();

    long countByOperationAndIsErrorFalse(String operation);
}