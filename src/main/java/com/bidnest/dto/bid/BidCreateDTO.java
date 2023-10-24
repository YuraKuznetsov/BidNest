package com.bidnest.dto.bid;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BidCreateDTO {

    @NotNull
    private Long auctionId;
    @DecimalMin(value = "0.0")
    @Digits(integer=7, fraction=2)
    private BigDecimal price;
}
