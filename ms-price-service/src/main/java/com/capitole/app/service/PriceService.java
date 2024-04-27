package com.capitole.app.service;

import com.capitole.app.model.entity.PriceEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public interface PriceService {

    // Find price by productId, brandId and date
    PriceEntity findPriceByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, OffsetDateTime date);

}