package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlStatsResponse;
import com.example.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String url,
                             @RequestParam(required = false) String alias){
        return urlService.createShortUrl(url,alias);
    }

    @GetMapping("/stats/{shortCode}")
    public UrlStatsResponse getStats(@PathVariable String shortCode){
        return urlService.getStats(shortCode);
    }
}
