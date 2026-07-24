package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;
import java.util.Optional;

public interface IQuantityMeasurementRepository {

    QuantityMeasurementEntity save(QuantityMeasurementEntity entity);

    Optional<QuantityMeasurementEntity> findById(Long id);

    List<QuantityMeasurementEntity> findAll();

    void deleteById(Long id);

    void deleteAll();
}