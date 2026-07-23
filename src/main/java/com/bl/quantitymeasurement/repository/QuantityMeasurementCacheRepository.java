package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static final String FILE_PATH = "quantity_measurement_repo.ser";
    private static QuantityMeasurementCacheRepository instance;
    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {
        loadFromFile();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
        saveToFile();
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(cache);
        } catch (IOException e) {
            System.err.println("Error persisting repository data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<QuantityMeasurementEntity> loaded = (List<QuantityMeasurementEntity>) ois.readObject();
            cache.addAll(loaded);
            System.out.println("Loaded " + loaded.size() + " records from storage.");
        } catch (Exception e) {
            System.err.println("Could not load stored records, starting fresh.");
        }
    }
}