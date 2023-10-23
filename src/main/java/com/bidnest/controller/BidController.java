package com.bidnest.controller;

import com.bidnest.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/...")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;
}
