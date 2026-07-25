package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private final Map<Long, QuantityMeasurementEntity> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);


    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        if (entity.getId() == null) {
            Long newId = idGenerator.incrementAndGet();
            entity.setId(newId);
        }
        store.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<QuantityMeasurementEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public void deleteAll() {
        store.clear();
        idGenerator.set(0);
    }
}