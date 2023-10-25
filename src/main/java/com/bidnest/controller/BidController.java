package com.bidnest.controller;

import com.bidnest.dto.bid.BidCreateDTO;
import com.bidnest.dto.bid.BidResponseDTO;
import com.bidnest.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auctions/{auctionId}/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @PostMapping
    public BidResponseDTO createBid(BidCreateDTO createDTO) {
        return bidService.createBid(createDTO);
    }
}
