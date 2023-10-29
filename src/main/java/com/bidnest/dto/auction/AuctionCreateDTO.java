package com.bidnest.dto.auction;

import com.bidnest.model.auction.Category;
import com.bidnest.model.auction.Currency;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class AuctionCreateDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String title;
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;
    @NotNull
    private Category category;
    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer=7, fraction=2)
    private BigDecimal initPrice;
    @NotNull
    private Currency currency;
    @NotNull
    private Timestamp startAt;
    private MultipartFile[] images;
}
