package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.util.ApplicationConfig;

public class RepositoryFactory {

    private static IQuantityMeasurementRepository instance;

    private RepositoryFactory() {
        // Private constructor for Singleton pattern
    }

    public static synchronized IQuantityMeasurementRepository getRepository() {
        if (instance == null) {
            String repoType = ApplicationConfig.getInstance().getProperty("repository.type", "database");

            if ("database".equalsIgnoreCase(repoType)) {
                instance = new QuantityMeasurementDatabaseRepository();
            } else if ("cache".equalsIgnoreCase(repoType) || "in-memory".equalsIgnoreCase(repoType)) {
                instance = new QuantityMeasurementCacheRepository();
            } else {
                System.err.println("Unknown repository.type [" + repoType + "]. Defaulting to QuantityMeasurementDatabaseRepository.");
                instance = new QuantityMeasurementDatabaseRepository();
            }
        }
        return instance;
    }

    public static synchronized void setRepository(IQuantityMeasurementRepository customRepo) {
        instance = customRepo;
    }

    public static synchronized void reset() {
        instance = null;
    }
}