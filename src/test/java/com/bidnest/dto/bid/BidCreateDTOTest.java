package com.bidnest.dto.bid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BidCreateDTOTest {

    private static Validator validator;

    @BeforeEach
    void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenLessThanZero_thenShouldGiveConstraintViolation() {
        BidCreateDTO bidDTO = new BidCreateDTO();
        bidDTO.setAuctionId(1L);
        bidDTO.setPrice(new BigDecimal("-10.00"));

        Set<ConstraintViolation<BidCreateDTO>> violations = validator.validate(bidDTO);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 0.0", violations.iterator().next().getMessage());
    }

    @Test
    public void whenMoreThanTwoFractionDigits_thenShouldGiveConstraintViolation() {
        BidCreateDTO bidDTO = new BidCreateDTO();
        bidDTO.setAuctionId(1L);
        bidDTO.setPrice(new BigDecimal("50.817"));

        Set<ConstraintViolation<BidCreateDTO>> violations = validator.validate(bidDTO);
        assertEquals(1, violations.size());
        assertEquals(
                "numeric value out of bounds (<7 digits>.<2 digits> expected)",
                violations.iterator().next().getMessage()
        );
    }

    @Test
    public void whenMoreThanSevenIntegerDigits_thenShouldGiveConstraintViolation() {
        BidCreateDTO bidDTO = new BidCreateDTO();
        bidDTO.setAuctionId(1L);
        bidDTO.setPrice(new BigDecimal("10000000.00"));

        Set<ConstraintViolation<BidCreateDTO>> violations = validator.validate(bidDTO);
        assertEquals(1, violations.size());
        assertEquals(
                "numeric value out of bounds (<7 digits>.<2 digits> expected)",
                violations.iterator().next().getMessage()
        );
    }

    @Test
    public void whenAuctionIdIsNull_thenShouldGiveConstraintViolations() {
        BidCreateDTO bidDTO = new BidCreateDTO();
        bidDTO.setAuctionId(null);
        bidDTO.setPrice(new BigDecimal("100.00"));

        Set<ConstraintViolation<BidCreateDTO>> violations = validator.validate(bidDTO);
        assertEquals(1, violations.size());
        for (ConstraintViolation<BidCreateDTO> violation : violations) {
            assertEquals("must not be null", violation.getMessage());
        }
    }

    @Test
    public void whenEverythingValid_thenShouldNotGiveConstraintViolations() {
        BidCreateDTO bidDTO = new BidCreateDTO();
        bidDTO.setAuctionId(1L);
        bidDTO.setPrice(new BigDecimal("100.00"));

        Set<ConstraintViolation<BidCreateDTO>> violations = validator.validate(bidDTO);
        assertTrue(violations.isEmpty());
    }
}