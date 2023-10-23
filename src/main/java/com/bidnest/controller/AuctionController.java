package com.bidnest.controller;

import com.bidnest.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/...")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;
}
