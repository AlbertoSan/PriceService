package com.capitole.app.controllers;

import com.capitole.app.model.entity.PriceEntity;
import com.capitole.openapi.model.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceAPIMapper {

    public PriceEntity asPriceEntity(PriceResponseDto priceResponseDto) {
        return PriceEntity.builder()
                .brandId(priceResponseDto.getBrandId())
                .startDate(priceResponseDto.getStartDate())
                .endDate(priceResponseDto.getEndDate())
                .priceList(priceResponseDto.getPriceList())
                .productId(priceResponseDto.getProductId())
                .price(priceResponseDto.getPrice())
                .curr(priceResponseDto.getCurr())
                .build();
    }

    public PriceResponseDto asPriceResponseDto(PriceEntity priceEntity) {
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        priceResponseDto.setBrandId(priceEntity.getBrandId());
        priceResponseDto.setStartDate(priceEntity.getStartDate());
        priceResponseDto.setEndDate(priceEntity.getEndDate());
        priceResponseDto.setPriceList(priceEntity.getPriceList());
        priceResponseDto.setProductId(priceEntity.getProductId());
        priceResponseDto.setPrice(priceEntity.getPrice());
        priceResponseDto.setCurr(priceEntity.getCurr());
        return priceResponseDto;
    }
}
