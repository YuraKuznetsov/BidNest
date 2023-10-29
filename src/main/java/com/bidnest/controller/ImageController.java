package com.bidnest.controller;

import com.bidnest.service.auction.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/...")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
}
