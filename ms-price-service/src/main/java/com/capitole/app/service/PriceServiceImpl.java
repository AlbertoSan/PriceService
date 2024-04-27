package com.capitole.app.service;

import com.capitole.app.exception.BrandNotFoundException;
import com.capitole.app.exception.PriceNotFoundException;
import com.capitole.app.exception.ProductNotFoundException;
import com.capitole.app.model.entity.PriceEntity;
import com.capitole.app.model.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepository repository;

    @Override
    public PriceEntity findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, OffsetDateTime date) {
        List<PriceEntity> prices = repository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                productId, brandId, date, date
        );
        if (!prices.isEmpty()) {
            return prices.stream()
                    .sorted(Comparator.comparingInt(PriceEntity::getPriority).reversed())
                    .findFirst()
                    .orElse(null);
        } else {
            List<PriceEntity> pricesByBrand = repository.findByBrandId(brandId);
            if(pricesByBrand.isEmpty()) {
                log.error("Brand not found");
                throw new BrandNotFoundException("Brand not found");
            }
            List<PriceEntity> pricesByProduct = repository.findByProductId(productId);
            if(pricesByProduct.isEmpty()) {
                log.error("Product not found");
                throw new ProductNotFoundException("Product not found");
            }
            log.error("Price not found for given parameters");
            throw new PriceNotFoundException("Price not found for given parameters");
        }
    }

    public List<PriceEntity> findAll() {
        return repository.findAll();
    }

    public PriceEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
