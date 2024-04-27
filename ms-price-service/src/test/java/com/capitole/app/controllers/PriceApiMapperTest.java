package com.capitole.app.controllers;

import com.capitole.app.model.entity.PriceEntity;
import com.capitole.openapi.model.PriceResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;

@RunWith(MockitoJUnitRunner.class)
public class PriceApiMapperTest {

    @InjectMocks
    private PriceAPIMapper mapper;

    private PriceEntity mockPrice;

    @Before
    public void setUp() {
        mockPrice = new PriceEntity();
        mockPrice.setPrice(123.45);
        mockPrice.setBrandId(1);
        mockPrice.setProductId(1);
        mockPrice.setStartDate(OffsetDateTime.now());
        mockPrice.setEndDate(OffsetDateTime.now());
        mockPrice.setPriority(1);
        mockPrice.setPriceList(1);
        mockPrice.setCurr("EUR");
    }

    @Test
    @DisplayName("Map price entity to price response")
    public void testMapPriceEntityToPriceResponse() {
        PriceResponseDto result = mapper.asPriceResponseDto(mockPrice);

        Assert.assertEquals(123.45, result.getPrice(), 0.0);
    }

    @Test
    @DisplayName("Map price response to price entity")
    public void testMapPriceResponseToPriceEntity() {
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setBrandId(1);
        priceResponseDto.setStartDate(OffsetDateTime.now());
        priceResponseDto.setEndDate(OffsetDateTime.now());
        priceResponseDto.setPriceList(1);
        priceResponseDto.setProductId(1);
        priceResponseDto.setPrice(123.45);
        priceResponseDto.setCurr("EUR");

        PriceEntity result = mapper.asPriceEntity(priceResponseDto);

        Assert.assertEquals(123.45, result.getPrice(), 0.0);
    }
}
