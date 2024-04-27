package com.capitole.app.service;

import com.capitole.app.model.entity.PriceEntity;
import com.capitole.app.model.repository.PriceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceServiceImpl service;

    private PriceEntity mockPrice;
    private List<PriceEntity> mockPrices;

    // Config mocks for all tests
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

        mockPrices = List.of(mockPrice);
    }

    @Test
    @DisplayName("Find price by productId, brandId and date")
    public void testFindPriceByProductIdAndBrandIdAndDate() {
        OffsetDateTime dateTime = OffsetDateTime.now();

        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, dateTime, dateTime))
                .thenReturn(mockPrices);

        PriceEntity result = service.findPriceByProductIdAndBrandIdAndDate(1, 1, dateTime);

        Assert.assertEquals(123.45, result.getPrice(), 0.0);
    }

    @Test
    @DisplayName("Find price by productId, brandId and date - Price not found")
    public void testFindPriceByProductIdAndBrandIdAndDatePriceNotFound() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, dateTime, dateTime))
                .thenReturn(List.of());
        when(repository.findByBrandId(1)).thenReturn(mockPrices);
        when(repository.findByProductId(1)).thenReturn(mockPrices);

        try {
            service.findPriceByProductIdAndBrandIdAndDate(1, 1, dateTime);
        } catch (Exception e) {
            Assert.assertEquals("Price not found for given parameters", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find price by productId, brandId and date - Brand not found")
    public void testFindPriceByProductIdAndBrandIdAndDateBrandNotFound() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, dateTime, dateTime))
                .thenReturn(List.of());
        when(repository.findByBrandId(1)).thenReturn(List.of());
        try {
            service.findPriceByProductIdAndBrandIdAndDate(1, 1, dateTime);
        } catch (Exception e) {
            Assert.assertEquals("Brand not found", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find price by productId, brandId and date - Product not found")
    public void testFindPriceByProductIdAndBrandIdAndDateProductNotFound() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        when(repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, dateTime, dateTime))
                .thenReturn(List.of());
        when(repository.findByBrandId(1)).thenReturn(mockPrices);
        when(repository.findByProductId(1)).thenReturn(List.of());

        try {
            service.findPriceByProductIdAndBrandIdAndDate(1, 1, dateTime);
        } catch (Exception e) {
            Assert.assertEquals("Product not found", e.getMessage());
        }
    }

    @Test
    @DisplayName("Find all prices")
    public void testFindAll() {
        when(repository.findAll()).thenReturn(mockPrices);

        List<PriceEntity> result = service.findAll();

        Assert.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Find price by id")
    public void testFindById() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(mockPrice));

        PriceEntity result = service.findById(1L);

        Assert.assertEquals(123.45, result.getPrice(), 0.0);
    }
}
