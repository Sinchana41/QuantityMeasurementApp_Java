package com.bl.quantitymeasurement;

import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.bl.quantitymeasurement.exception.QuantityMeasurementException;
import com.bl.quantitymeasurement.impl.QuantityMeasurementServiceImpl;
import com.bl.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.bl.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceImplTest {

    private FakeRepository fakeRepository;
    private IQuantityMeasurementService service;

    // Lightweight in-memory repository to eliminate external mocking dependencies
    static class FakeRepository implements IQuantityMeasurementRepository {
        private final List<QuantityMeasurementEntity> savedEntities = new ArrayList<>();

        @Override
        public void save(QuantityMeasurementEntity entity) {
            savedEntities.add(entity);
        }

        @Override
        public List<QuantityMeasurementEntity> getAllMeasurements() {
            return savedEntities;
        }

        public QuantityMeasurementEntity getLastSavedEntity() {
            return savedEntities.isEmpty() ? null : savedEntities.get(savedEntities.size() - 1);
        }

        public int getSaveCount() {
            return savedEntities.size();
        }
    }

    @BeforeEach
    void setUp() {
        fakeRepository = new FakeRepository();
        service = new QuantityMeasurementServiceImpl(fakeRepository);
    }

    @Test
    @DisplayName("compare() - should return true when comparing 1 Feet and 12 Inches")
    void testCompare_EqualValues_ReturnsTrue() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        boolean result = service.compare(feet, inches);

        assertTrue(result);
        assertEquals(1, fakeRepository.getSaveCount());
        assertEquals("COMPARE", fakeRepository.getLastSavedEntity().operation);
        assertEquals("true", fakeRepository.getLastSavedEntity().resultString);
    }

    @Test
    @DisplayName("compare() - should throw exception when comparing incompatible units")
    void testCompare_IncompatibleUnits_ThrowsException() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO liters = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITERS);

        assertThrows(QuantityMeasurementException.class, () -> service.compare(feet, liters));
        assertEquals(1, fakeRepository.getSaveCount());
        assertTrue(fakeRepository.getLastSavedEntity().isError);
    }

    @Test
    @DisplayName("convert() - should successfully convert 1 Feet to 12 Inches")
    void testConvert_FeetToInches_Success() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO targetInches = new QuantityDTO(0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = service.convert(feet, targetInches);

        assertNotNull(result);
        assertEquals(12.0, result.getValue(), 1e-6);
        assertEquals("INCHES", result.getUnit());
        assertEquals(1, fakeRepository.getSaveCount());
        assertEquals("CONVERT", fakeRepository.getLastSavedEntity().operation);
    }

    @Test
    @DisplayName("add() - should return 24 Inches when adding 1 Feet and 12 Inches in Inches target")
    void testAdd_TwoQuantities_ReturnsCorrectSum() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        QuantityDTO targetUnit = new QuantityDTO(0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = service.add(feet, inches, targetUnit);

        assertEquals(24.0, result.getValue(), 1e-6);
        assertEquals("INCHES", result.getUnit());
        assertEquals(1, fakeRepository.getSaveCount());
        assertEquals("ADD", fakeRepository.getLastSavedEntity().operation);
        assertEquals(24.0, fakeRepository.getLastSavedEntity().resultValue, 1e-6);
    }

    @Test
    @DisplayName("subtract() - should return 0 Feet when subtracting 12 Inches from 1 Feet")
    void testSubtract_TwoQuantities_ReturnsCorrectDifference() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = service.subtract(feet, inches);

        assertEquals(0.0, result.getValue(), 1e-6);
        assertEquals("FEET", result.getUnit());
        assertEquals("SUBTRACT", fakeRepository.getLastSavedEntity().operation);
    }

    @Test
    @DisplayName("divide() - should return 5.0 when dividing 10 Feet by 2 Feet")
    void testDivide_ValidQuantities_ReturnsQuotient() {
        QuantityDTO dividend = new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO divisor = new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET);

        double result = service.divide(dividend, divisor);

        assertEquals(5.0, result, 1e-6);
        assertEquals("DIVIDE", fakeRepository.getLastSavedEntity().operation);
    }

    @Test
    @DisplayName("divide() - should throw exception when dividing by zero")
    void testDivide_ByZero_ThrowsException() {
        QuantityDTO dividend = new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO divisor = new QuantityDTO(0.0, QuantityDTO.LengthUnit.FEET);

        assertThrows(QuantityMeasurementException.class, () -> service.divide(dividend, divisor));
        assertEquals(1, fakeRepository.getSaveCount());
        assertTrue(fakeRepository.getLastSavedEntity().isError);
    }
}