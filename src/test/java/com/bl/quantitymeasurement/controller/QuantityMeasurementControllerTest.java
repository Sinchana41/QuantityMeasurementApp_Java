package com.bl.quantitymeasurement.controller;

import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.repository.IQuantityMeasurementRepository;

import com.bl.quantitymeasurement.repository.RepositoryFactory;
import com.bl.quantitymeasurement.service.impl.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        // Instantiate actual dependencies directly using pure JUnit setup
        IQuantityMeasurementRepository repository = RepositoryFactory.getRepository();
        repository.deleteAll();

        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    @Test
    @DisplayName("Controller should accurately perform comparison using underlying domain logic")
    void testPerformComparison_Success() {
        QuantityDTO feetDto = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO inchesDto = new QuantityDTO(12.0, "INCHES", "LengthUnit");

        boolean result = controller.performComparison(feetDto, inchesDto);

        assertTrue(result);
    }

    @Test
    @DisplayName("Controller should accurately perform addition and return resulting DTO")
    void testPerformAddition_Success() {
        QuantityDTO feetDto = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO inchesDto = new QuantityDTO(12.0, "INCHES", "LengthUnit");

        QuantityDTO resultDto = controller.performAddition(feetDto, inchesDto);

        assertNotNull(resultDto);
        assertEquals(2.0, resultDto.getValue(), 0.01);
        assertEquals("FEET", resultDto.getUnit());
    }

    @Test
    @DisplayName("Controller should accurately perform conversion and return target DTO")
    void testPerformConversion_Success() {
        QuantityDTO sourceDto = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO targetDto = new QuantityDTO(0.0, "INCHES", "LengthUnit");

        QuantityDTO resultDto = controller.performConversion(sourceDto, targetDto);

        assertNotNull(resultDto);
        assertEquals(12.0, resultDto.getValue(), 0.01);
        assertEquals("INCHES", resultDto.getUnit());
    }
}