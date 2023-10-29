package com.bidnest.controller;

import com.bidnest.dto.auction.AuctionCreateDTO;
import com.bidnest.dto.auction.AuctionResponseDTO;
import com.bidnest.dto.auction.AuctionUpdateDTO;
import com.bidnest.service.auction.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auctions/")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    public AuctionResponseDTO createAuction(AuctionCreateDTO createDTO) {
        return auctionService.createAuction(createDTO);
    }

    @GetMapping("/{id}")
    public AuctionResponseDTO getAuctionById(@PathVariable Long id) {
        return auctionService.getAuctionResponseDTOById(id);
    }

    @PutMapping("/{id}")
    public void updateAuction(AuctionUpdateDTO updateDTO) {
        auctionService.updateAuction(updateDTO);
    }
}
