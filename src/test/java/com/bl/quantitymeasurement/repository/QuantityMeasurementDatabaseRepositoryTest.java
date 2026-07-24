package com.bl.quantitymeasurement.repository;

import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.bl.quantitymeasurement.exception.DatabaseException;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeAll
    static void initEnvironment() {
        System.setProperty("app.env", "test");
    }

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("testDatabaseRepository_SaveEntity: Verifies entity saved and assigned generated ID")
    void testDatabaseRepository_SaveEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true");
        QuantityMeasurementEntity savedEntity = repository.save(entity);

        assertNotNull(savedEntity.getId(), "Generated ID should not be null after insert");

        List<QuantityMeasurementEntity> measurements = repository.findAll();
        assertEquals(1, measurements.size());
        assertEquals("COMPARE", measurements.get(0).getOperation());
        assertEquals("1.0 FEET", measurements.get(0).getOperand1());
        assertEquals("12.0 INCHES", measurements.get(0).getOperand2());
        assertEquals("true", measurements.get(0).getResultString());
    }

    @Test
    @DisplayName("testDatabaseRepository_UpdateEntity: Verifies entity update logic")
    void testDatabaseRepository_UpdateEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", 2.0);
        QuantityMeasurementEntity savedEntity = repository.save(entity);

        savedEntity.setResultValue(24.0);
        savedEntity.setResultString("24.0 INCHES");
        repository.save(savedEntity);

        Optional<QuantityMeasurementEntity> retrieved = repository.findById(savedEntity.getId());
        assertTrue(retrieved.isPresent());
        assertEquals(24.0, retrieved.get().getResultValue(), 0.01);
        assertEquals("24.0 INCHES", retrieved.get().getResultString());
    }

    @Test
    @DisplayName("testDatabaseRepository_RetrieveAllMeasurements: Verifies findAll() returns all entities")
    void testDatabaseRepository_RetrieveAllMeasurements() {
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true"));
        repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", 2.0));

        List<QuantityMeasurementEntity> measurements = repository.findAll();
        assertEquals(2, measurements.size());
    }

    @Test
    @DisplayName("testDatabaseRepository_FindById: Verifies fetching entity by ID")
    void testDatabaseRepository_FindById() {
        QuantityMeasurementEntity saved = repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true"));

        Optional<QuantityMeasurementEntity> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("COMPARE", found.get().getOperation());
    }

    @Test
    @DisplayName("testDatabaseRepository_DeleteById: Verifies removing single entity")
    void testDatabaseRepository_DeleteById() {
        QuantityMeasurementEntity saved1 = repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true"));
        QuantityMeasurementEntity saved2 = repository.save(new QuantityMeasurementEntity("ADD", "1.0 FEET", "12.0 INCHES", 2.0));

        repository.deleteById(saved1.getId());

        List<QuantityMeasurementEntity> measurements = repository.findAll();
        assertEquals(1, measurements.size());
        assertEquals(saved2.getId(), measurements.get(0).getId());
    }

    @Test
    @DisplayName("testDatabaseRepository_DeleteAll: Verifies truncate table empties database")
    void testDatabaseRepository_DeleteAll() {
        repository.save(new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true"));
        assertEquals(1, repository.findAll().size());

        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    @DisplayName("testParameterizedQuery_DateTimeHandling: Verifies timestamp mapping")
    void testParameterizedQuery_DateTimeHandling() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true");
        LocalDateTime customTime = LocalDateTime.now().minusDays(1);
        entity.setTimestamp(customTime);

        QuantityMeasurementEntity saved = repository.save(entity);
        Optional<QuantityMeasurementEntity> fetched = repository.findById(saved.getId());

        assertTrue(fetched.isPresent());
        assertNotNull(fetched.get().getTimestamp());
    }

    @Test
    @DisplayName("testDatabaseRepository_SaveErrorEntity: Verifies storing error response")
    void testDatabaseRepository_SaveErrorEntity() {
        QuantityMeasurementEntity errorEntity = new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "1.0 GALLON", "Incompatible units", true);
        QuantityMeasurementEntity saved = repository.save(errorEntity);

        Optional<QuantityMeasurementEntity> fetched = repository.findById(saved.getId());
        assertTrue(fetched.isPresent());
        assertTrue(fetched.get().isError());
        assertEquals("Incompatible units", fetched.get().getErrorMessage());
    }

    @Test
    @DisplayName("testNullSave_ThrowsException: Defensive check on null argument")
    void testNullSave_ThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            repository.save(null);
        });
    }

    @Test
    @DisplayName("testUpdateNonExistentId_ThrowsDatabaseException: Handles missing update targets")
    void testUpdateNonExistentId_ThrowsDatabaseException() {
        QuantityMeasurementEntity missingEntity = new QuantityMeasurementEntity("COMPARE", "1.0 FEET", "12.0 INCHES", "true");
        missingEntity.setId(99999L);

        assertThrows(DatabaseException.class, () -> {
            repository.save(missingEntity);
        });
    }
}