package com.bl.quantitymeasurement;

import com.bl.quantitymeasurement.controller.QuantityMeasurementController;
import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementControllerTest {

    private FakeService fakeService;
    private QuantityMeasurementController controller;

    // Fake Service to test Controller API delegation in pure JUnit 5
    static class FakeService implements IQuantityMeasurementService {
        public boolean compareCalled = false;
        public boolean convertCalled = false;
        public boolean addCalled = false;
        public boolean subtractCalled = false;
        public boolean divideCalled = false;

        @Override
        public boolean compare(QuantityDTO q1, QuantityDTO q2) {
            compareCalled = true;
            return true;
        }

        @Override
        public QuantityDTO convert(QuantityDTO source, QuantityDTO targetUnit) {
            convertCalled = true;
            return new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
        }

        @Override
        public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
            addCalled = true;
            return new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET);
        }

        @Override
        public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit) {
            addCalled = true;
            return new QuantityDTO(24.0, QuantityDTO.LengthUnit.INCHES);
        }

        @Override
        public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
            subtractCalled = true;
            return new QuantityDTO(0.0, QuantityDTO.LengthUnit.FEET);
        }

        @Override
        public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, QuantityDTO targetUnit) {
            subtractCalled = true;
            return new QuantityDTO(0.0, QuantityDTO.LengthUnit.INCHES);
        }

        @Override
        public double divide(QuantityDTO q1, QuantityDTO q2) {
            divideCalled = true;
            return 5.0;
        }
    }

    @BeforeEach
    void setUp() {
        fakeService = new FakeService();
        controller = new QuantityMeasurementController(fakeService);
    }

    @Test
    @DisplayName("performComparison() - should delegate call to service")
    void testPerformComparison_DelegatesToService() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        boolean result = controller.performComparison(feet, inches);

        assertTrue(result);
        assertTrue(fakeService.compareCalled);
    }

    @Test
    @DisplayName("performConversion() - should delegate call to service")
    void testPerformConversion_DelegatesToService() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO targetInches = new QuantityDTO(0.0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = controller.performConversion(feet, targetInches);

        assertNotNull(result);
        assertEquals(12.0, result.getValue());
        assertTrue(fakeService.convertCalled);
    }

    @Test
    @DisplayName("performAddition() - should delegate call to service")
    void testPerformAddition_DelegatesToService() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = controller.performAddition(feet, inches);

        assertNotNull(result);
        assertEquals(2.0, result.getValue());
        assertTrue(fakeService.addCalled);
    }

    @Test
    @DisplayName("performSubtraction() - should delegate call to service")
    void testPerformSubtraction_DelegatesToService() {
        QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO inches = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

        QuantityDTO result = controller.performSubtraction(feet, inches);

        assertNotNull(result);
        assertEquals(0.0, result.getValue());
        assertTrue(fakeService.subtractCalled);
    }

    @Test
    @DisplayName("performDivision() - should delegate call to service")
    void testPerformDivision_DelegatesToService() {
        QuantityDTO dividend = new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET);
        QuantityDTO divisor = new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET);

        double result = controller.performDivision(dividend, divisor);

        assertEquals(5.0, result, 1e-6);
        assertTrue(fakeService.divideCalled);
    }
}