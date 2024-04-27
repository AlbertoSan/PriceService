package com.capitole.app.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capitole.app.model.entity.PriceEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer productId, Integer brandId, OffsetDateTime date1, OffsetDateTime date2
    );

    List<PriceEntity> findByBrandId(Integer brandId);

    List<PriceEntity> findByProductId(Integer productId);
}
