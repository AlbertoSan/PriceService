package com.capitole.app.controllers;


import com.capitole.app.model.entity.PriceEntity;
import com.capitole.app.service.PriceServiceImpl;
import com.capitole.openapi.api.PricesApi;
import com.capitole.openapi.model.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    @Autowired
    private PriceServiceImpl priceService;

    private final PriceAPIMapper priceAPIMapper;
    @Override
    public ResponseEntity<PriceResponseDto> getPrices(
            @Valid Integer brandId,
            @Valid Integer productId,
            @Valid OffsetDateTime date)
    {
        log.debug("getPrices: brandId={}, productId={}, date={}", brandId, productId, date);
        PriceEntity price = priceService.findPriceByProductIdAndBrandIdAndDate(productId, brandId, date);
        PriceResponseDto priceResponseDto = priceAPIMapper.asPriceResponseDto(price);
        return ResponseEntity.ok(priceResponseDto);
    }
}
