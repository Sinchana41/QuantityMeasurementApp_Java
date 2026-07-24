package com.bl.quantitymeasurement.service.impl;

import com.bl.quantitymeasurement.dto.QuantityDTO;
import com.bl.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.bl.quantitymeasurement.repository.RepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceImplTest {

    private QuantityMeasurementServiceImpl service;
    private IQuantityMeasurementRepository repository;

    private QuantityDTO feetDTO;
    private QuantityDTO inchesDTO;
    private QuantityDTO gallonDTO;

    @BeforeEach
    void setUp() {
        // Initialize in-memory cache repository to test real state without external dependencies
        repository = RepositoryFactory.getRepository();
        repository.deleteAll();

        service = new QuantityMeasurementServiceImpl(repository);

        feetDTO = new QuantityDTO(1.0, "FEET", "LengthUnit");
        inchesDTO = new QuantityDTO(12.0, "INCHES", "LengthUnit");
        gallonDTO = new QuantityDTO(1.0, "GALLON", "VolumeUnit");
    }

    @Test
    @DisplayName("Compare 1 Feet and 12 Inches should return true and save measurement to repository")
    void testCompare_EqualQuantities() {
        boolean result = service.compare(feetDTO, inchesDTO);

        assertTrue(result);
    }


    @Test
    @DisplayName("Addition of 1 Feet and 12 Inches should equal 2 feet")
    void testAdd_ValidQuantities() {
        QuantityDTO result = service.add(feetDTO, inchesDTO);

        assertNotNull(result);
        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals("FEET", result.getUnit());

    }

    @Test
    @DisplayName("Conversion of 1 Feet to Inches should return 12 Inches")
    void testConvert_ValidUnits() {
        QuantityDTO targetDTO = new QuantityDTO(0.0, "INCHES", "LengthUnit");

        QuantityDTO result = service.convert(feetDTO, targetDTO);

        assertNotNull(result);
        assertEquals(12.0, result.getValue(), 0.01);
        assertEquals("INCHES", result.getUnit());
    }
}