package com.bidnest.service;

import com.bidnest.exception.ItemNotFoundException;
import com.bidnest.model.auction.Auction;
import com.bidnest.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public Auction findById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Auction not found"));
    }
}
