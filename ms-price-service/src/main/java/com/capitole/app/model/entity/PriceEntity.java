package com.capitole.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICE_ENTITY")
public class PriceEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private double price;
    private String curr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceEntity)) return false;
        PriceEntity price = (PriceEntity) o;
        return Objects.equals(getId(), price.getId()) &&
                Objects.equals(getBrandId(), price.getBrandId()) &&
                Objects.equals(getStartDate(), price.getStartDate()) &&
                Objects.equals(getEndDate(), price.getEndDate()) &&
                Objects.equals(getPriceList(), price.getPriceList()) &&
                Objects.equals(getProductId(), price.getProductId()) &&
                Objects.equals(getPriority(), price.getPriority()) &&
                Objects.equals(getPrice(), price.getPrice()) &&
                Objects.equals(getCurr(), price.getCurr());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
