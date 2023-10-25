package com.bidnest.dto.bid;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class BidResponseDTO {

    private Long id;
    private String bidderFullName;
    private BigDecimal price;
    private Timestamp placedAt;
}
