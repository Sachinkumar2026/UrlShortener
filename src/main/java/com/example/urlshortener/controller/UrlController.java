package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlListResponse;
import com.example.urlshortener.dto.UrlStatsResponse;
import com.example.urlshortener.service.UrlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/all")
    public Page<UrlListResponse>getAllUrls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size ){
        return urlService.getAllUrls(PageRequest.of(page,size));
    }

    @DeleteMapping("/{shortCode}")
    public String deleteUrl(@PathVariable String shortCode){
        urlService.deleteUrl(shortCode);
        return "Short URl deleted successfully";
    }
}
