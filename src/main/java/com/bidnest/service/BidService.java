package com.bidnest.service;

import com.bidnest.dto.bid.BidCreateDTO;
import com.bidnest.dto.bid.BidMapper;
import com.bidnest.dto.bid.BidResponseDTO;
import com.bidnest.model.Bid;
import com.bidnest.model.auction.Auction;
import com.bidnest.model.user.User;
import com.bidnest.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidService {

    private final UserService userService;
    private final AuctionService auctionService;
    private final BidRepository bidRepository;
    private final BidMapper bidMapper;

    public BidResponseDTO createBid(BidCreateDTO createDTO) {
        User bidder = userService.getCurrentUser();
        Auction auction = auctionService.findById(createDTO.getAuctionId());
        Bid bid = bidMapper.createDTOToBid(createDTO);
        bidder.addBid(bid);
        auction.addBid(bid);

        return bidMapper.bidToResponseDTO(bidRepository.save(bid));
    }
}
